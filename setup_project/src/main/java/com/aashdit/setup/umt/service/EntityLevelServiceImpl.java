package com.aashdit.setup.umt.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.dto.CurrentUserVo;
import com.aashdit.setup.umt.dto.EntityIdAndUserLevel;
import com.aashdit.setup.umt.model.HierarchyEntityMap;
import com.aashdit.setup.umt.model.OrganizationStructureHierarchy;
import com.aashdit.setup.umt.model.Role;
import com.aashdit.setup.umt.model.RoleEntityMap;
import com.aashdit.setup.umt.model.RoleLevelMap;
import com.aashdit.setup.umt.model.RoleRightLevelMaster;
import com.aashdit.setup.umt.model.UmtNativeQuery;
import com.aashdit.setup.umt.model.User;
import com.aashdit.setup.umt.repository.HierarchyEntityMapRepository;
import com.aashdit.setup.umt.repository.OrganizationStructureHierarchyRepository;
import com.aashdit.setup.umt.repository.RoleEntityMapRepository;
import com.aashdit.setup.umt.repository.RoleRepository;
import com.aashdit.setup.umt.repository.UmtNativeQueryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EntityLevelServiceImpl implements EntityLevelService {

	@Autowired
	private AccessService accessService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleEntityMapRepository roleEntityMapRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private HierarchyEntityMapRepository hierarchyEntityMapRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationStructureHierarchyRepository organizationStructureHierarchyRepository;

	@Autowired
	private UmtNativeQueryRepository umtNativeQueryRepository;

	private final EntityManager entityManager;

	@Autowired
	public EntityLevelServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Map<String, Object> createAndUpdateUser(String roleCode, String classTypeKey) {
		ServiceOutcome<Role> roleByCode = roleService.getRoleByCode(roleCode);
		Map<String, Object> userMap = new HashMap<>();
		Long roleId = 0L;
		String entityLevel = "";
		Long roleLevelMapId = 0L;
		Long roleRightLevelId = 0L;
		if (roleByCode.getOutcome() && roleByCode.getData() != null) {
			roleId = roleByCode.getData().getRoleId();
		} else {
			Role role = addUpdateRole(roleCode);
			ServiceOutcome<Role> roleServiceOutcome = roleService.addNupdateRole(role);
			if (roleServiceOutcome.getOutcome() && roleServiceOutcome.getData() != null) {
				roleId = roleServiceOutcome.getData().getRoleId();
			}
		}

		Optional<RoleRightLevelMaster> levelMasterByLevelCode = roleService
				.getRoleRightLevelMasterByLevelKeyClass(classTypeKey);
		if (levelMasterByLevelCode.isPresent()) {
			roleRightLevelId = levelMasterByLevelCode.get().getRoleRightLevelId();
			Optional<RoleLevelMap> roleAndLevel = roleService.findRoleLevelMapByRoleAndLevel(roleId, roleRightLevelId);
			if (!roleAndLevel.isPresent()) {
				ServiceOutcome<RoleLevelMap> roleLevelMapServiceOutcome = roleService.addNupdateRoleLevelMap(
						new Long[] { -1L }, new Long[] { roleRightLevelId }, new Boolean[] { true }, roleId);
				if (roleLevelMapServiceOutcome.getOutcome() && roleLevelMapServiceOutcome.getData() != null) {
					roleLevelMapId = roleLevelMapServiceOutcome.getData().getRoleLevelId();
				}
			} else {
				roleLevelMapId = roleAndLevel.get().getRoleLevelId();
			}
			entityLevel = levelMasterByLevelCode.get().getLevelCode();
		}

		userMap.put("roleId", roleId);
		userMap.put("entityLevel", entityLevel);
		userMap.put("roleRightLevelId", roleRightLevelId);
		userMap.put("roleLevelMapId", roleLevelMapId);
		return userMap;

	}

	private static Role addUpdateRole(String roleCode) {
		String roleName;
		// if roleCode contains ROLE_ then remove it
		if (roleCode.contains("ROLE_")) {
			roleName = roleCode.substring(5);
		} else {
			roleName = roleCode;
		}
		Role role = new Role();
		role.setRoleCode(roleCode);
		role.setDisplayName(roleName);
		role.setIsActive(true);
		role.setDisplayOnPage(true);
		role.setDescription(roleName);
		role.setMaxAssignments(-1L);
		return role;
	}

	@Override
	public Map<String, Object> createUserAsPerNewEntityLevel(String roleCode, String userType, String email,
			String firstName, String lastName, String phoneNo, Long objectId, String classTypeKey,
			Long[] primaryRoleIds, String code) {
		Map<String, Object> roleMap = this.createAndUpdateUser(roleCode, classTypeKey);
		Long[] roleIds = new Long[] { (Long) roleMap.get("roleId") };
		String entityLevel = (String) roleMap.get("entityLevel");

		ServiceOutcome<User> muUser = userService.addUser(email, firstName, lastName, null, phoneNo, email, roleIds,
				primaryRoleIds, userType, userType, entityLevel, objectId, new Long[] { 0L }, code);

		roleMap.put("userId", muUser.getData().getUserId());

		if (muUser.getOutcome() && muUser.getData() != null) {
			Long roleRightLevelId = (Long) roleMap.get("roleRightLevelId");
			Long userId = muUser.getData().getUserId();
			Long roleId = roleIds[0];
			ServiceOutcome<String> config = accessService.saveConfig(userId, roleId, roleRightLevelId, objectId);
			if (config.getOutcome()) {
				roleMap.put("config", config.getData());
			}
		}
		return roleMap;
	}

	// recursive call to get all parent object ids and level from parent object id
	// making two list of ids and list of levels
	@Override
	public void getParentObjectIdsAndLevel(Long objectId, String objectLevel,
			List<CurrentUserVo.AllEntityIdsAndLevel> allEntityIdsAndLevels) {
		try {
			hierarchyEntityMapRepository.findByOrgHirAndEntityIdAndEntityType(objectId, objectLevel)
					.ifPresent(hierarchyEntityMap -> {
						if (hierarchyEntityMap.getParentObjectId() != null
								&& hierarchyEntityMap.getParentObjectType() != null) {
							CurrentUserVo.AllEntityIdsAndLevel allEntityIdsAndLevel = new CurrentUserVo.AllEntityIdsAndLevel();
							allEntityIdsAndLevel.setEntityId(hierarchyEntityMap.getParentObjectId());
							allEntityIdsAndLevel.setEntityLevel(hierarchyEntityMap.getParentObjectType());
							allEntityIdsAndLevel.setEntityIdAndLevel(hierarchyEntityMap.getParentObjectId() + "##"
									+ hierarchyEntityMap.getParentObjectType());
							// if all parent object ids and level and IdAndLevel are already present, then
							// return
							if (allEntityIdsAndLevels.stream()
									.anyMatch(entityIdsAndLevel -> entityIdsAndLevel.getEntityIdAndLevel()
											.equals(hierarchyEntityMap.getParentObjectId() + "##"
													+ hierarchyEntityMap.getParentObjectType()))) {
								return;
							}
							allEntityIdsAndLevels.add(allEntityIdsAndLevel);
							getParentObjectIdsAndLevel(hierarchyEntityMap.getParentObjectId(),
									hierarchyEntityMap.getParentObjectType(), allEntityIdsAndLevels);
						}
					});
		} catch (Exception e) {
			log.error("Error in EntityLevelServiceImpl::getAllParentObjectIdsAndLevel", e);
		}
	}

	@Override
	public List<OrganizationStructureHierarchy> getLevelListByOrganization(String orgData) {
		List<OrganizationStructureHierarchy> levelList = null;
		try {
			if (orgData.contains("##"))
				levelList = organizationStructureHierarchyRepository.findAllUniqueByOrganizationStructureId(
						Long.valueOf(orgData.split("##")[0]), String.valueOf(orgData.split("##")[1]));
			levelList = levelList.stream()
					.collect(Collectors.toCollection(
							() -> new TreeSet<>(Comparator.comparing(OrganizationStructureHierarchy::getLevelCode))))
					.stream().collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levelList;
	}

	@Override
	public List<EntityIdAndUserLevel> getActualLevelNameListByLevelCodeAndOrgId(String levelData) {
		List<EntityIdAndUserLevel> entityIdAndUserLevels = null;
		try {
			if (levelData.contains("##")) {
				List<HierarchyEntityMap> hierarchyEntityMaps = hierarchyEntityMapRepository
						.findAllByOrganizationStructureHierarchyOrganizationStructureObjectIdAndOrganizationStructureHierarchyOrganizationStructureObjectTypeAndObjectTypeAndIsActiveTrue(
								Long.valueOf(levelData.split("##")[0]), levelData.split("##")[2],
								levelData.split("##")[1]);
				List<Long> entityIds = hierarchyEntityMaps.stream().map(HierarchyEntityMap::getObjectId)
						.collect(Collectors.toList());
				entityIdAndUserLevels = getDynamicEntityList(levelData.split("##")[1], entityIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityIdAndUserLevels;
	}

	@SuppressWarnings("unchecked")
	private List<EntityIdAndUserLevel> getDynamicEntityList(String entityCode, List<Long> entityIds) {
		List<EntityIdAndUserLevel> entityIdAndUserLevelList = new ArrayList<>();
		Optional<UmtNativeQuery> nativeQuery = umtNativeQueryRepository.findByQueryCode(entityCode.trim());
		if (nativeQuery.isPresent()) {
			UmtNativeQuery query = nativeQuery.get();
			String sql = query.getQuery();
			Query qr = entityManager.createNativeQuery(sql);
			qr.setParameter("objectId", entityIds);
			List<Object[]> resultList = qr.getResultList();
			for (Object[] obj : resultList) {
				EntityIdAndUserLevel map = new EntityIdAndUserLevel();
				long entityId = obj[0] != null ? Long.parseLong(obj[0].toString()) : 0;
				String name = obj[1] != null ? obj[1].toString() : "";
				String userLevel = obj[2] != null ? obj[2].toString() : "";
				map.setEntityId(entityId);
				map.setOrganizationName(name);
				map.setUserLevel(userLevel);
				map.setCombineTwo(entityId + "##" + userLevel);
				entityIdAndUserLevelList.add(map);
			}
		}
		return entityIdAndUserLevelList;
	}

	@Override
	public List<Role> getRoleListByActualLevel(String actualLevelData) {
		Set<Role> rolesSet = new HashSet<>();
		List<Role> rolesList = new ArrayList<>();
		try {
			if (actualLevelData.contains("##")) {
				List<RoleEntityMap> roleEntityMaps = roleEntityMapRepository
						.findAllByEntityIdAndEntityLevelAndIsActiveTrue(Long.valueOf(actualLevelData.split("##")[0]),
								actualLevelData.split("##")[1]);
//				boolean isPublicRoleNeeded = roleEntityMaps.stream().anyMatch(RoleEntityMap::getIsPublicRoleNeeded);
				List<String> roleCodes = roleEntityMaps.stream().map(RoleEntityMap::getRoleCode)
						.collect(Collectors.toList());
				List<Role> roles = roleRepository.findAllByIsActiveTrueAndRoleCodeIn(roleCodes);
				rolesSet.addAll(roles);
//				if (isPublicRoleNeeded) {
//					List<Role> roleList = roleRepository.findByRoleAccessType(CommonUMTConstants.ROLE_ACCESS_TYPE_PUBLIC);
//					rolesSet.addAll(roleList);
//				}
				// sort in name ascending order
				rolesList = rolesSet.stream().sorted(Comparator.comparing(Role::getDisplayName))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rolesList;
	}

}
