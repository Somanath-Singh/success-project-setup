package com.aashdit.prod.heads.common.service;


import com.aashdit.prod.heads.common.dto.DuplicateCheckDto;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;

public interface CommonValidationService {
	
	ServiceOutcome<DuplicateCheckDto> checkDuplicateByAnyCode(String code, Long Id, String type);
	
	ServiceOutcome<DuplicateCheckDto> checkDuplicateByAnyName(String name, Long Id,String type);

}
