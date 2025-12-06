package com.aashdit.setup.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.setup.common.dto.ItemOfWorkDto;
import com.aashdit.setup.common.model.ItemOfWork;
import com.aashdit.setup.common.repository.ItemOfWorkRepository;
import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.model.User;
import com.aashdit.setup.umt.utils.SecurityHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemOfWorkServiceImpl implements ItemOfWorkService {

    @Autowired
    private ItemOfWorkRepository itemOfWorkRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ServiceOutcome<Boolean> saveOrUpdateItemOfWork(ItemOfWorkDto dto) {
        ServiceOutcome<Boolean> outcome = new ServiceOutcome<>();
        String message = "msg.error";
        try {
            User user = SecurityHelper.getCurrentUser();
            ItemOfWork entity = null;

            if (dto.getId() != null) {
                entity = itemOfWorkRepository.findByIdAndIsActiveTrue(dto.getId());
            }

            if (Optional.ofNullable(entity).isPresent()) {
                entity.setName(dto.getName());
                entity.setDescription(dto.getDescription() != null ? dto.getDescription() : "N/A");
                entity.setIsActive(dto.getIsActive());
                entity.setLastUpdatedBy(user);
                entity.setLastUpdatedOn(new Date());
                message = "msg.item.update";
            } else {
                entity = new ItemOfWork();
                entity.setName(dto.getName());
                entity.setDescription(dto.getDescription() != null ? dto.getDescription() : "N/A");
                entity.setIsActive(true);
                entity.setCreatedBy(user);
                entity.setCreatedOn(new Date());
                message = "msg.item.success";
            }

            itemOfWorkRepository.save(entity);
            
            outcome.setOutcome(true);
            outcome.setMessage(messageSource.getMessage(message, null, LocaleContextHolder.getLocale()));

        } catch (Exception e) {
            outcome.setOutcome(false);
            outcome.setMessage(messageSource.getMessage(message, null, LocaleContextHolder.getLocale()));
            log.error("Exception occured in saveOrUpdateItemOfWork()  of ItemOfWorkServiceImpl : " + e.getMessage());
			e.printStackTrace();
        }
        return outcome;
    }

    @Override
    public ServiceOutcome<List<ItemOfWorkDto>> getAllActiveItemsOfWork() {
        ServiceOutcome<List<ItemOfWorkDto>> outcome = new ServiceOutcome<>();
        List<ItemOfWorkDto> dtoList = new ArrayList<>();
        try {
            List<ItemOfWork> list = itemOfWorkRepository.findAll();
            dtoList = list.stream().map(entity -> {
                ItemOfWorkDto dto = new ItemOfWorkDto();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setDescription(entity.getDescription());
                dto.setIsActive(entity.getIsActive());
                return dto;
            }).collect(Collectors.toList());
            
            outcome.setData(dtoList);
            outcome.setOutcome(true);
        } catch (Exception e) {
        	log.error("Exception occured in getAllActiveItemsOfWork()  of ItemOfWorkServiceImpl : " + e.getMessage());
			e.printStackTrace();
			outcome.setData(dtoList);
            outcome.setOutcome(false);
        }
        return outcome;
    }

    @Override
    public ServiceOutcome<ItemOfWorkDto> getActiveItemOfWorkById(Long id) {
        ServiceOutcome<ItemOfWorkDto> outcome = new ServiceOutcome<>();
        ItemOfWorkDto dto = new ItemOfWorkDto();
        try {
            ItemOfWork entity = itemOfWorkRepository.findByIdAndIsActiveTrue(id);
            if (entity != null) {
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setDescription(entity.getDescription());
                dto.setIsActive(entity.getIsActive());
                outcome.setData(dto);
                outcome.setOutcome(true);
            }
        } catch (Exception e) {
        	log.error("Exception occured in getActiveItemOfWorkById()  of ItemOfWorkServiceImpl : " + e.getMessage());
			e.printStackTrace();
			outcome.setData(dto);
            outcome.setOutcome(false);
        }
        return outcome;
    }

    @Override
    public ServiceOutcome<Boolean> checkDuplicateName(String name, Long id) {
        ServiceOutcome<Boolean> outcome = new ServiceOutcome<>();
        try {
            List<ItemOfWork> items = itemOfWorkRepository.validateNameAndIsActiveTrue(name);
            boolean isDuplicate = false;

            if (items != null && !items.isEmpty()) {
                isDuplicate = items.stream().anyMatch(item ->
                        id == null || !item.getId().equals(id)
                );
            }
            
            outcome.setData(isDuplicate);
            outcome.setOutcome(true);
        } catch (Exception e) {
            outcome.setOutcome(false);
            log.error("Exception occurred in checkDuplicateName of ItemOfWorkServiceImpl => {}", e.getMessage(), e);
        }
        return outcome;
    }

	@Override
	public ServiceOutcome<Boolean> validateWorkItemStatus(Long id) {
		ServiceOutcome<Boolean> outcome = new ServiceOutcome<>();
        try {
        	ItemOfWork entity = itemOfWorkRepository.findById(id).get();
        	boolean status = entity.getIsActive() ? false : true ;
        	
        	if(entity.getIsActive()) {
        		entity.setIsActive(false);
        	}else {
        		entity.setIsActive(true);
        	}
        	itemOfWorkRepository.save(entity);
        	
        	outcome.setData(status);
            outcome.setOutcome(true);
        } catch (Exception e) {
            outcome.setOutcome(false);
            log.error("Exception occurred in validateWorkItemStatus() of ItemOfWorkServiceImpl => {}", e.getMessage());
            e.printStackTrace();
        }
        return outcome;
	}
    
}