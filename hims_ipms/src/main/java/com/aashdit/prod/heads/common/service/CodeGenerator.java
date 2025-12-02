package com.aashdit.prod.heads.common.service;

import javax.validation.Valid;

import com.aashdit.prod.heads.common.dto.GenerateTopCodeDTO;
import com.aashdit.prod.heads.common.utils.ModuleType;

public interface CodeGenerator {

	String generateTopLevelProjectCode(@Valid GenerateTopCodeDTO codeDto);
	
	String generateSubModuleCode(ModuleType moduleType, String parentProjectCode);
	
}
