package com.aashdit.prod.heads.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.aashdit.prod.heads.common.dto.MunicipalityVo;
import com.aashdit.prod.heads.common.model.Municipality;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;

public interface MunicipalityService {

	List<Municipality> getAllMunicipalities(Boolean isActive);
	
	Map<String, Object> getMunicipalitiesLoadingData(Long entityId, String level);
	
	ServiceOutcome<Municipality> addOrUpdateMunicipality(MunicipalityVo municipalityVo, MultipartFile icon);
	
	Municipality getMunicipalityById(Long municipalityId);
	
	ServiceOutcome<Boolean> updateActiveStatusMunicipality(Long municipalityId, Boolean status);

	List<Municipality> getAllMunicipalitiesByGovId(Long govId, Boolean isActive);

    List<Municipality> getAllMunicipalitiesByParentIdAndParentLevel(Long entityId, String entityType);
    
}
