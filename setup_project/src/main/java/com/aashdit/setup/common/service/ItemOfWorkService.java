package com.aashdit.setup.common.service;

import java.util.List;

import com.aashdit.setup.common.dto.ItemOfWorkDto;
import com.aashdit.setup.core.ServiceOutcome;

public interface ItemOfWorkService {

	ServiceOutcome<Boolean> saveOrUpdateItemOfWork(ItemOfWorkDto dto);

	ServiceOutcome<List<ItemOfWorkDto>> getAllActiveItemsOfWork();

	ServiceOutcome<ItemOfWorkDto> getActiveItemOfWorkById(Long id);
	
	ServiceOutcome<Boolean> checkDuplicateName(String name, Long id);

	ServiceOutcome<Boolean> validateWorkItemStatus(Long id);

}
