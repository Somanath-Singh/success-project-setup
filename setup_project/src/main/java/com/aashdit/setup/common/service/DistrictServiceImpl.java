package com.aashdit.setup.common.service;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.setup.common.model.District;
import com.aashdit.setup.common.repository.DistrictRepository;
import com.aashdit.setup.common.repository.StateRepository;
import com.aashdit.setup.core.ServiceOutcome;

@Service
public class DistrictServiceImpl implements DistrictService , MessageSourceAware{

    final static Logger logger = Logger.getLogger(DistrictServiceImpl.class);

    private MessageSource messageSource;
    
    @Autowired
    private DistrictRepository districtRepository;
    
    @Autowired
    private StateRepository stateRepository;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    @Override
    public ServiceOutcome<List<District>> getAllDistrict(Boolean isActive) {

		ServiceOutcome<List<District>> svcOutcome = new ServiceOutcome<>();
		List<District> lstDistricts = new ArrayList<>();
		Boolean outCome=false;
		String message="msg.error";
		try {
			if(isActive==null) {
				lstDistricts = districtRepository.findAll();
				
			}else {
				lstDistricts = districtRepository.findAllByIsActiveOrderByDistrictNameAsc(isActive);
			}

//			lstDistricts = new LocaleSpecificSorter<District>(District.class).sort(lstDistricts);
			outCome=true;
			message="msg.success";
			svcOutcome.setData(lstDistricts);
		} catch (Exception ex) {
			logger.error(ex);
		}
		svcOutcome.setData(lstDistricts);
		svcOutcome.setOutcome(outCome);
		svcOutcome.setMessage(messageSource.getMessage(message, null, LocaleContextHolder.getLocale()));
		return svcOutcome;
	    }

    @Override
    public ServiceOutcome<District> getById(Long districtId) {
		ServiceOutcome<District> svcOutcome = new ServiceOutcome<>();
		try {
			District districts = districtRepository.getOne(districtId);
			svcOutcome.setData(districts);
		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	    }

    @Override
    public ServiceOutcome<List<District>> search(District searchParams) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceOutcome<District> saveDistrict(District district) {

		ServiceOutcome<District> svcOutcome = new ServiceOutcome<>();
		try {
			if (district.getDistrictId() != null) {
				District prvDistrict = districtRepository.getOne(district.getDistrictId());
				prvDistrict.setDistrictCode(district.getDistrictCode());
				prvDistrict.setDistrictName(district.getDistrictName());
				prvDistrict.setDistrictNameHi(district.getDistrictNameHi());
				prvDistrict.setStateId(stateRepository.getOne(district.getStateId().getStateId()));
				prvDistrict.setIsActive(district.getIsActive());
				prvDistrict = districtRepository.save(prvDistrict);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			} else {
				district.setDistrictName(district.getDistrictName());
				district = districtRepository.save(district);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}

		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	    }

    @Override
    public ServiceOutcome<District> updateDistrict(District district) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceOutcome<District> deleteDistrict(Long districtId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public ServiceOutcome<List<District>> listByStateId(Long stateId) {
		ServiceOutcome<List<District>> svcOutcome = new ServiceOutcome<>();
		try {
			List<District> districts = districtRepository.findAllByStateId(stateId, true);
			svcOutcome.setData(districts);
		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	}


}
