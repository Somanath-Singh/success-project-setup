package com.aashdit.setup.common.service;

import com.aashdit.setup.common.model.CodeGenerationConfig;

public interface CodeGenerationConfigService {
	
	CodeGenerationConfig getActiveConfigByModuleType(String moduleType);
    
}