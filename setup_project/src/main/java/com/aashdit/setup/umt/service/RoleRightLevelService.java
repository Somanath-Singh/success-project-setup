package com.aashdit.setup.umt.service;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.model.RoleRightLevelMaster;

import java.util.List;

public interface RoleRightLevelService {

	ServiceOutcome<List<RoleRightLevelMaster>> getRoleRightLevelsByRoleId(Long roleId);
	
	ServiceOutcome<List<RoleRightLevelMaster>> getRoleRightLevels(Boolean includeInActive);

	ServiceOutcome<List<RoleRightLevelMaster>> getLevelPagedList();
	
	ServiceOutcome<RoleRightLevelMaster> fndByLevelId(Long levelId);
}
