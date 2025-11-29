package com.aashdit.prod.heads.hims.umt.service;

import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.umt.model.RoleRightLevelMaster;

import java.util.List;

public interface RoleRightLevelService {

	ServiceOutcome<List<RoleRightLevelMaster>> getRoleRightLevelsByRoleId(Long roleId);
	
	ServiceOutcome<List<RoleRightLevelMaster>> getRoleRightLevels(Boolean includeInActive);

	ServiceOutcome<List<RoleRightLevelMaster>> getLevelPagedList();
	
	ServiceOutcome<RoleRightLevelMaster> fndByLevelId(Long levelId);
}
