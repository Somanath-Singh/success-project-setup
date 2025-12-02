package com.aashdit.prod.heads.common.service;

import com.aashdit.prod.heads.common.model.CodeGenerationConfig;

public interface CodeGenerationConfigService {
	
	CodeGenerationConfig getActiveConfigByModuleType(String moduleType);
    
}