package com.aashdit.prod.heads.hims.umt.service;

import java.util.List;
import java.util.Map;

import com.aashdit.prod.heads.hims.umt.dto.CurrentUserVo;
import com.aashdit.prod.heads.hims.umt.dto.EntityIdAndUserLevel;
import com.aashdit.prod.heads.hims.umt.model.OrganizationStructureHierarchy;
import com.aashdit.prod.heads.hims.umt.model.Role;

public interface EntityLevelService {

    Map<String, Object> createAndUpdateUser(String roleCode, String classTypeKey);

    Map<String, Object> createUserAsPerNewEntityLevel(String roleCode, String userType, String email,
                                                      String firstName, String lastName,
                                                      String phoneNo, Long objectId,
                                                      String classTypeKey,
                                                      Long[] primaryRoleIds,
                                                      String code);

    void getParentObjectIdsAndLevel(Long objectId, String objectLevel, List<CurrentUserVo.AllEntityIdsAndLevel> allEntityIdsAndLevels);
    
	List<OrganizationStructureHierarchy> getLevelListByOrganization(String orgData);

	List<EntityIdAndUserLevel> getActualLevelNameListByLevelCodeAndOrgId(String levelData);
	
	List<Role> getRoleListByActualLevel(String actualLevelData);

}
