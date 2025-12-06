package com.aashdit.setup.umt.service;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.model.*;
import com.google.gson.JsonArray;

import java.util.List;
import java.util.Optional;

public interface RoleService {

	ServiceOutcome<List<Role>> getAllRoles(Boolean includeInactive);
	
	ServiceOutcome<List<Role>> getRoleForUser(Long userId);
	
	ServiceOutcome<Role> getRoleByCode(String roleCode);

	ServiceOutcome<Role> findByRoleId(Long roleId);

	ServiceOutcome<Role> lockNUnlockRoleById(Long roleId, Boolean isActive);

	ServiceOutcome<Role> addNupdateRole(Role role);

	Boolean roleEntityMap(String[] roleCodes, Class<?> entityClassName, Long id);

	ServiceOutcome<List<RoleLevelMap>> findRoleLevelList();

	ServiceOutcome<List<RoleLevelMap>> findRoleLevelListByRoleId(Long roleId);

	ServiceOutcome<List<RoleRightLevelMaster>> roleRightLevelList();

	ServiceOutcome<RoleLevelMap> addNupdateRoleLevelMap(Long[] maxAllocations, Long[] roleLevelId, Boolean[] status, Long roleId);

	ServiceOutcome<RoleRightLevelMaster> getRoleRightLevelMasterById(Long levelId);

	ServiceOutcome<RoleLevelMap> findRoleLevelMapByRoleIdAndLevelId(Long roleId, Long levelId);
	
	Optional<RoleLevelMap> findRoleLevelMapByRoleAndLevel(Long roleId, Long levelId);

	ServiceOutcome<List<Role>> findRoleList();

	/* Angular Methods Below */
	ServiceOutcome<List<Role>> getRolePagedList();
	
	ServiceOutcome<Role> save(Role role);
	
	ServiceOutcome<Role> delete(Long roleId);
	
	ServiceOutcome<Boolean> allocateMenu(RoleMenuMap roleMenuMap);
	
	ServiceOutcome<Boolean> deAllocateMenu(RoleMenuMap roleMenuMap);

	ServiceOutcome<RoleLevelMap> allocateLevel(RoleLevelMap roleLevelMap);

	ServiceOutcome<RoleLevelMap> deAllocateLevel(RoleLevelMap roleLevelMap);


	JsonArray getAllRolesByEntityIdAndUserLevel(String entityIdAndUserLevel);
	
	JsonArray getEntitySpecificRoles(Long entityId, String userLevel);

	List<Role> getEntitySpecificRoleList(Long entityId, String userLevel);

	Optional<RoleRightLevelMaster> getRoleRightLevelMasterByLevelKeyClass(String levelCode);

	Optional<RoleLevelMap> findRoleLevelMapByRoleAndRoleLevelMapId(Long primaryRoleId, Long roleLevelId);

	Optional<RoleRightLevelMaster> getRoleRightLevelMasterByCode(String objectLevel);

	Optional<Role> getRoleByRoleId(Long primaryRoleId);

	List<RoleEntityMap> getEntitySpecificRoleEntityMapList(Long id, String levelCode);

	List<Role> getEntitySpecificSelectedRoleList(Long selectedEntityId, String selectedEntityLevel);

	List<Role> getAllRollsByUser();

	List<Role> getEntityOnlySpecificRoleList(long l, String s);
	
	ServiceOutcome<List<Role>> getUpperLevelRoleListByRoleId(Long roleId);

	ServiceOutcome<List<Role>> getLowerLevelRoleListByRoleId(Long roleId);

	List<String> getAllRoleCodesActiveForMobile();

	ServiceOutcome<List<Role>> getAllRolesActiveForMobile();

}
