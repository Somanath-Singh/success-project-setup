package com.aashdit.setup.common.service;

import com.aashdit.setup.common.utils.ModuleType;

public interface GenericCodeStrategy {
	
	void validateModuleHierarchy(ModuleType moduleType);
	
	String findMaxCode(String prefix,ModuleType moduleType);
	
}
