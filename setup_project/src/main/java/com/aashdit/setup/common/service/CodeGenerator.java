package com.aashdit.setup.common.service;

import javax.validation.Valid;

import com.aashdit.setup.common.dto.GenerateTopCodeDTO;
import com.aashdit.setup.common.utils.ModuleType;

public interface CodeGenerator {

	String generateTopLevelProjectCode(@Valid GenerateTopCodeDTO codeDto);
	
	String generateSubModuleCode(ModuleType moduleType, String parentProjectCode);
	
}
