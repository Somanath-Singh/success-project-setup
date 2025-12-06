package com.aashdit.setup.common.service;


import com.aashdit.setup.common.dto.DuplicateCheckDto;
import com.aashdit.setup.core.ServiceOutcome;

public interface CommonValidationService {
	
	ServiceOutcome<DuplicateCheckDto> checkDuplicateByAnyCode(String code, Long Id, String type);
	
	ServiceOutcome<DuplicateCheckDto> checkDuplicateByAnyName(String name, Long Id,String type);

}
