package com.aashdit.prod.heads.common.service;

import java.util.List;

import com.aashdit.prod.heads.hims.ipms.model.EntityAppModuleMap;

public interface EntityModuleMapService {

	void entityModuleMap(List<Long> moduleIds, Long entityId, Class<?> entityClass);

	void entityModuleMap(String[] moduleCodes, Long entityId, Class<?> entityClass);

	List<Long> getAppModuleIdByEntityIdAndCode(Long governingBodyId, Class<?> entityClass);

	List<EntityAppModuleMap> getPublicAndEntitySpecificEntityLevelCodeListList(Long entityId, String entityLevel);
}
