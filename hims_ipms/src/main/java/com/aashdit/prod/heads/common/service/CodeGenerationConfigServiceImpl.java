package com.aashdit.prod.heads.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashdit.prod.heads.common.model.CodeGenerationConfig;
import com.aashdit.prod.heads.common.repository.CodeGenerationConfigRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CodeGenerationConfigServiceImpl implements CodeGenerationConfigService {
    
	@Autowired
    private CodeGenerationConfigRepository codeGenerationConfigRepository;
    
	@Override
	public CodeGenerationConfig getActiveConfigByModuleType(String moduleType) {
		CodeGenerationConfig codeConfig = new CodeGenerationConfig();
		try {
			if (moduleType != null) {
				codeConfig = codeGenerationConfigRepository.getCodeGenerationConfigByModuleName(moduleType);
			}
		} catch (Exception e) {
			log.error("Exception occured in getActiveConfigByModuleType of CodeGenerationConfigServiceImpl :" + e.getMessage());
			e.printStackTrace();
		}
		return codeConfig;
	}
    
}