package com.aashdit.prod.heads.hims.umt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.umt.dto.AccesslevelConfigMetaInfo;
import com.aashdit.prod.heads.hims.umt.model.Role;
import com.aashdit.prod.heads.hims.umt.model.RoleRightLevelMaster;
import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.model.UserRoleMap;
import com.aashdit.prod.heads.hims.umt.specs.AccessLevelType;

public interface AccessService {

	<T> ServiceOutcome<List<T>> getByRoleLevel(Long userId, Long roleId, AccessLevelType accessLevel, Class<T> returnType);
	
	ServiceOutcome<AccesslevelConfigMetaInfo> getACLConfigData(Long userId, Long roleId, Long roleRightLevelId, String searchTerm, Integer page, Integer size);
	
	ServiceOutcome<String> saveConfig(Long userId, Long roleId, Long roleRightLevelId, Long objectId);

	<T> ServiceOutcome<List<T>> getByRoleLevel(Long userId, Long roleId, String accessLevelCode, Class<T> returnType);
	
	/* API Methods */
	ServiceOutcome<String> allocate(Long userId, Long roleId, Long roleRightLevelId, Long objectId);
	
	ServiceOutcome<String> deAllocate(Long userId, Long roleId, Long roleRightLevelId, Long objectId);
	
	ServiceOutcome<User> getUsersWithAccess(Long entityId, String roleLevelCode, Long roleId);
	
	ServiceOutcome<List<Role>> findRolesFromUserRoleMap(Long userId);

	ServiceOutcome<UserRoleMap> getUserRoleMapByMapId(Long userRoleMapId);

//	ServiceOutcome<List<CommonNameIdDto>> getAccessListToShowInDashboard(User user);

	ServiceOutcome<List<Object[]>> getCoreDataByQuery(String encValue);


	Optional<RoleRightLevelMaster> getRoleRightLevelMasterByClass(Class<?> entityClass);

	Map<String, Object> getLevelMasterEntityNameByObjectType(Long objectId, String objectType);

	Map<String, Object> getLevelMasterEntityNameByObjectType(Long objectId, String objectType, String... fields);
	Map<String, Object> getLevelMasterEntityNameByObjectTypeWithColumns(Long objectId, String objectType, String... fields);

	Map<String, String> setGbOrMunNameAndIcon(List<String> allEntityIdsAndLevel);

}
