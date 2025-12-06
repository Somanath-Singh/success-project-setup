package com.aashdit.setup.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.aashdit.setup.common.dto.GoverningBodyVo;
import com.aashdit.setup.common.model.GoverningBody;
import com.aashdit.setup.core.ServiceOutcome;

public interface GoverningBodyService {

	Map<String, Object> getGoverningBodyLoadingData();

	ServiceOutcome<GoverningBody> addOrUpdateGoverningBody(GoverningBodyVo governingBodyVo, MultipartFile icon);

	GoverningBody getGoverningBodyById(Long governingBodyId);

	ServiceOutcome<Boolean> updateActiveStatusGoverningBody(Long governingBodyId, Boolean status);

	List<GoverningBody> getAllGoverningBodies(Boolean isActive);

	Boolean checkDuplicateEmail(String email) throws Exception;
	
}
