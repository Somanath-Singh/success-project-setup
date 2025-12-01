package com.aashdit.prod.heads.hims.umt.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.aashdit.prod.heads.framework.core.LocaleSpecificSorter;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.umt.model.Role;
import com.aashdit.prod.heads.hims.umt.model.RoleEntityMap;
import com.aashdit.prod.heads.hims.umt.model.RoleLevelMap;
import com.aashdit.prod.heads.hims.umt.model.RoleMenuMap;
import com.aashdit.prod.heads.hims.umt.model.RoleRightLevelMaster;
import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.repository.RoleEntityMapRepository;
import com.aashdit.prod.heads.hims.umt.repository.RoleLevelMapRepository;
import com.aashdit.prod.heads.hims.umt.repository.RoleMenuMapRepository;
import com.aashdit.prod.heads.hims.umt.repository.RoleRepository;
import com.aashdit.prod.heads.hims.umt.repository.RoleRightLevelMasterRepository;
import com.aashdit.prod.heads.hims.umt.utils.SecurityHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class RoleServiceImpl implements RoleService, MessageSourceAware {

	private final Logger log = Logger.getLogger(this.getClass());

	private MessageSource messageSource;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleEntityMapRepository roleEntityMapRepository;

	@Autowired
	private RoleLevelMapRepository roleLevelMapRepository;

	@Autowired
	private RoleRightLevelMasterRepository roleRightLevelMasterRepository;

	@Autowired
	private RoleMenuMapRepository roleMenuMapRepository;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public ServiceOutcome<List<Role>> getAllRoles(Boolean includeInactive) {
		ServiceOutcome<List<Role>> svcOutcome = new ServiceOutcome<>();

		try {
			List<Role> lstRoles = null;
			if (!includeInactive) {
				lstRoles = roleRepository.findByIsActiveEqual(true);
			} else {
				lstRoles = roleRepository.findAll();
			}
			lstRoles = lstRoles.stream().sorted((first, second) -> {
				return first.getDisplayName().compareTo(second.getDisplayName());
			}).collect(Collectors.toList());

			svcOutcome.setData(lstRoles);
		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceOutcome<List<Role>> getRoleForUser(Long userId) {
		ServiceOutcome<List<Role>> svcOutcome = new ServiceOutcome<>();

		try {
			List<Role> lstRoles = null;

			String query = "select r.*  " + "from t_umt_role r " + "join t_umt_user_role_map urm "
					+ "  on urm.role_id = r.role_id " + "  and urm.user_id = :userId ";

			Query rs = entityManager.createNativeQuery(query, Role.class);
			rs.setParameter("userId", userId);
			lstRoles = rs.getResultList();
			lstRoles = new LocaleSpecificSorter<Role>(Role.class).sort(lstRoles);

			svcOutcome.setData(lstRoles);
		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Role> getRoleByCode(String roleCode) {
		ServiceOutcome<Role> svcOutcome = new ServiceOutcome<Role>();
		try {
			Role role = roleRepository.findByRoleCode(roleCode);
			svcOutcome.setData(role);
		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Role> findByRoleId(Long roleId) {
		ServiceOutcome<Role> svcOutcome = new ServiceOutcome<Role>();
		try {
			Role role = roleRepository.findById(roleId).get();
			List<RoleEntityMap> byRoleIdEqual = roleEntityMapRepository.findByRoleCodeEqual(role.getRoleCode());
			if (!byRoleIdEqual.isEmpty()) {
				RoleEntityMap roleEntityMap = byRoleIdEqual.get(0);
				role.setObjectIdAndType(roleEntityMap.getEntityId() + "##" + roleEntityMap.getEntityLevel());
			}
			svcOutcome.setData(role);
		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Role> lockNUnlockRoleById(Long roleId, Boolean isActive) {
		ServiceOutcome<Role> serviceOutcome = new ServiceOutcome<Role>();
		try {
			if (roleId != null) {
				Role role = roleRepository.findById(roleId).get();
				if (isActive) {
					role.setIsActive(isActive);
					serviceOutcome.setMessage(messageSource.getMessage("msg.success", null,
							"role unlocked successfully", LocaleContextHolder.getLocale()));
				}

				if (!isActive) {
					role.setIsActive(isActive);
					serviceOutcome.setMessage(messageSource.getMessage("msg.success", null, "role locked successfully",
							LocaleContextHolder.getLocale()));
				}
				role = roleRepository.save(role);
				serviceOutcome.setData(role);
			} else {
				serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
				serviceOutcome.setData(null);
				serviceOutcome.setOutcome(false);
			}

		} catch (Exception e) {
			log.error(e);
			serviceOutcome.setData(null);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<Role> addNupdateRole(Role role) {
		ServiceOutcome<Role> outcome = new ServiceOutcome<Role>();
		try {
			User user = SecurityHelper.getCurrentUser();
			if (role.getRoleId() != null) {
				Role prvRole = roleRepository.findById(role.getRoleId()).get();
				prvRole.setUpdatedOn(new Date());
				prvRole.setUpdateBy(user.getUserId());
				prvRole.setDescription(role.getDescription());
				prvRole.setDisplayName(role.getDisplayName());
				prvRole.setMaxAssignments(role.getMaxAssignments());
				String objectIdAndType = role.getObjectIdAndType();
				prvRole = roleRepository.save(prvRole);
				prvRole.setObjectIdAndType(objectIdAndType);
				roleEntityMap(prvRole);
				outcome.setData(prvRole);
				outcome.setMessage(messageSource.getMessage("role.msg.update.success", null,
						"Role updated successfully", LocaleContextHolder.getLocale()));
			} else {
				// role=new Role();
				role.setCreatedBy(user.getUserId());
				role.setCreatedOn(new Date());
				role.setIsActive(true);
				role.setDisplayOnPage(true);
				String displayName = role.getDisplayName();
				// Make role code from display name by adding trim then ROLE_ prefix and
				// converting to uppercase and replacing spaces with underscor
				String roleCode = "ROLE_" + displayName.trim().toUpperCase().replace(" ", "_");
				Role byRoleCode = roleRepository.findByRoleCode(roleCode);
				if (byRoleCode != null) {
					outcome.setData(null);
					outcome.setOutcome(false);
					outcome.setMessage(messageSource.getMessage("role.msg.duplicate.error", null, "Role already exists",
							LocaleContextHolder.getLocale()));
					return outcome;
				}
				role.setRoleCode(roleCode);
				role = roleRepository.save(role);
				roleEntityMap(role);
				outcome.setData(role);
				outcome.setMessage(messageSource.getMessage("role.msg.save.success", null, "Role saved successfully",
						LocaleContextHolder.getLocale()));
			}
		} catch (Exception e) {
			log.error(e);
			outcome.setData(null);
			outcome.setOutcome(false);
			outcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return outcome;
	}

	private void roleEntityMap(Role role) {
		if (role.getObjectIdAndType() != null && !role.getObjectIdAndType().isEmpty()) {
			String[] split = roleEntityAddAndUpdate(role);
			role.setRoleAccessType("PRIVATE");
			roleRepository.save(role);

			RoleRightLevelMaster byLevelCode = roleRightLevelMasterRepository.findByLevelCode(split[1]);
			if (byLevelCode != null) {
				Long[] maxAllocations = { -1L };
				Long roleId = Long.parseLong(split[0]);
				Boolean[] status = { true };
				Long[] roleLevelId = { byLevelCode.getRoleRightLevelId() };
				this.addNupdateRoleLevelMap(maxAllocations, roleLevelId, status, roleId);
			}
		}
	}

	@Override
	public Boolean roleEntityMap(String[] roleCodes, Class<?> entityClassName, Long id) {
		for (String roleCode : roleCodes) {
			Role role = roleRepository.findByRoleCode(roleCode);
			if (role != null) {
				Optional<RoleRightLevelMaster> lvlMst = roleRightLevelMasterRepository
						.findByLevelKeyClass(entityClassName.getSimpleName() + ".class");
				if (lvlMst.isPresent()) {
					String objectIdAndType = id + "##" + lvlMst.get().getLevelCode();
					role.setObjectIdAndType(objectIdAndType);
					roleEntityAddAndUpdate(role);
				}
			}
		}
		return true;
	}

	private String[] roleEntityAddAndUpdate(Role role) {
		String[] split = role.getObjectIdAndType().split("##");
		RoleEntityMap roleEntityMap = new RoleEntityMap();
		Optional<RoleEntityMap> level = roleEntityMapRepository.findByEntityIdAndLevelRoleCode(Long.parseLong(split[0]),
				split[1], role.getRoleCode());
		if (level.isPresent()) {
			roleEntityMap = level.get();
		} else {
			roleEntityMap.setIsActive(true);
		}
		roleEntityMap.setEntityId(Long.parseLong(split[0]));
		roleEntityMap.setEntityLevel(split[1]);
		roleEntityMap.setRoleCode(role.getRoleCode());
		roleEntityMap.setIsPublicRoleNeeded(true);
		roleEntityMapRepository.save(roleEntityMap);
		return split;
	}

	@Override
	public ServiceOutcome<List<RoleLevelMap>> findRoleLevelList() {
		ServiceOutcome<List<RoleLevelMap>> serviceOutcome = new ServiceOutcome<List<RoleLevelMap>>();
		try {
			List<RoleLevelMap> roleLevelMaps = roleLevelMapRepository.findAll();
			serviceOutcome.setData(roleLevelMaps);
		} catch (Exception e) {
			log.error(e);
			serviceOutcome.setData(null);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<List<RoleLevelMap>> findRoleLevelListByRoleId(Long roleId) {
		ServiceOutcome<List<RoleLevelMap>> serviceOutcome = new ServiceOutcome<List<RoleLevelMap>>();
		try {
			List<RoleLevelMap> roleLevelMaps = roleLevelMapRepository.findByRoleId(roleId);
			serviceOutcome.setData(roleLevelMaps);
		} catch (Exception e) {
			log.error(e);
			serviceOutcome.setData(null);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<List<RoleRightLevelMaster>> roleRightLevelList() {
		ServiceOutcome<List<RoleRightLevelMaster>> serviceOutcome = new ServiceOutcome<List<RoleRightLevelMaster>>();
		try {
			List<RoleRightLevelMaster> roleLevelMaps = roleRightLevelMasterRepository.findByIsActive(true);
			serviceOutcome.setData(roleLevelMaps);
		} catch (Exception e) {
			log.error(e);
			serviceOutcome.setData(null);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<RoleLevelMap> addNupdateRoleLevelMap(Long[] maxAllocations, Long[] roleLevelId,
			Boolean[] status, Long roleId) {
		ServiceOutcome<RoleLevelMap> roleServiceOutcome = new ServiceOutcome<RoleLevelMap>();
		User user = SecurityHelper.getCurrentUser();
		try {
			for (int i = 0; i < roleLevelId.length; i++) {
				log.debug("roleLevelId is : " + roleLevelId[i]);
				RoleLevelMap roleLevelMap = roleLevelMapRepository.findByRoleIdAndLevelId(roleId, roleLevelId[i]);
				if (roleLevelMap != null) {
					log.debug("Found link for role : " + roleId + " and levelId : " + roleLevelId[i]);
					roleLevelMap.setMaxAllocations(maxAllocations[i]);
					roleLevelMap.setIsActive(status[i]);

					roleLevelMap.setUpdatedOn(new Date());
					roleLevelMap.setUpdateBy(user.getUserId());

					log.debug("saving entry for above ");
					roleLevelMap = roleLevelMapRepository.save(roleLevelMap);
				} else {
					log.debug("No link found for role : " + roleId + " and levelId : " + roleLevelId[i]);
					if (status[i]) {
						roleLevelMap = new RoleLevelMap();
						roleLevelMap.setRoleId(roleId);
						roleLevelMap.setLevelId(roleLevelId[i]);
						roleLevelMap.setMaxAllocations(maxAllocations[i]);
						roleLevelMap.setIsActive(status[i]);

						roleLevelMap.setCreatedOn(new Date());
						roleLevelMap.setCreatedBy(user.getUserId());

						log.debug("creating entry for above ");
						roleLevelMap = roleLevelMapRepository.save(roleLevelMap);
					}
				}

			}
			roleServiceOutcome
					.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
		} catch (Exception e) {
			log.error(e);
			roleServiceOutcome.setData(null);
			roleServiceOutcome.setOutcome(false);
			roleServiceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return roleServiceOutcome;
	}

	@Override
	public ServiceOutcome<RoleRightLevelMaster> getRoleRightLevelMasterById(Long levelId) {
		ServiceOutcome<RoleRightLevelMaster> serviceOutcome = new ServiceOutcome<RoleRightLevelMaster>();
		try {
			RoleRightLevelMaster roleRightLevelMaster = roleRightLevelMasterRepository.getOne(levelId);
			serviceOutcome.setData(roleRightLevelMaster);
		} catch (Exception e) {
			log.error(e);
			serviceOutcome.setData(null);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<RoleLevelMap> findRoleLevelMapByRoleIdAndLevelId(Long roleId, Long levelId) {
		ServiceOutcome<RoleLevelMap> serviceOutcome = new ServiceOutcome<RoleLevelMap>();
		try {
			RoleLevelMap roleLevelMap = roleLevelMapRepository.findByRoleIdAndLevelId(roleId, levelId);
			serviceOutcome.setData(roleLevelMap);
			serviceOutcome.setOutcome(true);
		} catch (Exception e) {
			log.error(e);
			serviceOutcome.setData(null);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return serviceOutcome;
	}

	@Override
	public Optional<RoleLevelMap> findRoleLevelMapByRoleAndLevel(Long roleId, Long levelId) {
		Optional<RoleLevelMap> roleLevelMap = Optional.empty();
		try {
			RoleLevelMap byRoleIdAndLevelId = roleLevelMapRepository.findByRoleIdAndLevelId(roleId, levelId);
			if (byRoleIdAndLevelId != null) {
				roleLevelMap = Optional.of(byRoleIdAndLevelId);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return roleLevelMap;
	}

	@Override
	public ServiceOutcome<List<Role>> findRoleList() {
		ServiceOutcome<List<Role>> serviceOutcome = new ServiceOutcome<List<Role>>();
		try {
			List<Role> roles = roleRepository.findAll();
			serviceOutcome.setData(roles);
		} catch (Exception e) {
			log.error(e);
			serviceOutcome.setData(null);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<List<Role>> getRolePagedList() {
		ServiceOutcome<List<Role>> serviceOutcome = new ServiceOutcome<List<Role>>();
		try {
			List<Role> userList = roleRepository.findAll();
			serviceOutcome.setData(userList);
		} catch (Exception e) {
			log.error(e.getMessage());
			serviceOutcome.setData(null);
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<Role> save(Role role) {
		return this.addNupdateRole(role);
	}

	@Override
	public ServiceOutcome<Role> delete(Long roleId) {
		return this.lockNUnlockRoleById(roleId, true);
	}

	@Override
	public ServiceOutcome<Boolean> allocateMenu(RoleMenuMap roleMenuMap) {
		ServiceOutcome<Boolean> svcOutcome = new ServiceOutcome<Boolean>();
		try {
			User currentUser = SecurityHelper.getCurrentUser();
			RoleMenuMap rmDBMap = roleMenuMapRepository.findByRoleIdAndMenuId(roleMenuMap.getRoleId(),
					roleMenuMap.getMenuId());
			if (rmDBMap != null && currentUser != null) {

				rmDBMap.setIsActive(true);
				rmDBMap.setUpdateBy(currentUser.getUserId());
				rmDBMap.setUpdatedOn(new Date());

				roleMenuMapRepository.save(rmDBMap);

				svcOutcome.setData(true);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			} else {
				roleMenuMap.setIsActive(true);
				roleMenuMap.setCreatedBy(currentUser.getUserId());
				roleMenuMap.setCreatedOn(new Date());

				roleMenuMapRepository.save(roleMenuMap);

				svcOutcome.setData(true);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}
		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

		}
		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Boolean> deAllocateMenu(RoleMenuMap roleMenuMap) {
		ServiceOutcome<Boolean> svcOutcome = new ServiceOutcome<Boolean>();
		try {
			User currentUser = SecurityHelper.getCurrentUser();
			RoleMenuMap rmDBMap = roleMenuMapRepository.findByRoleIdAndMenuId(roleMenuMap.getRoleId(),
					roleMenuMap.getMenuId());
			if (rmDBMap != null) {

				rmDBMap.setIsActive(false);
				rmDBMap.setUpdateBy(currentUser.getUserId());
				rmDBMap.setUpdatedOn(new Date());

				roleMenuMapRepository.save(rmDBMap);

				svcOutcome.setData(true);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}
		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

		}
		return svcOutcome;
	}

	@Override
	public ServiceOutcome<RoleLevelMap> allocateLevel(RoleLevelMap roleLevelMap) {

		Long[] maxAllocations = new Long[] { roleLevelMap.getMaxAllocations() };
		Long[] roleLevelId = new Long[] { roleLevelMap.getLevelId() };
		Boolean[] status = new Boolean[] { true };
		Long roleId = roleLevelMap.getRoleId();

		return addNupdateRoleLevelMap(maxAllocations, roleLevelId, status, roleId);

	}

	@Override
	public ServiceOutcome<RoleLevelMap> deAllocateLevel(RoleLevelMap roleLevelMap) {
		Long[] maxAllocations = new Long[] { roleLevelMap.getMaxAllocations() };
		Long[] roleLevelId = new Long[] { roleLevelMap.getLevelId() };
		Boolean[] status = new Boolean[] { false };
		Long roleId = roleLevelMap.getRoleId();

		return addNupdateRoleLevelMap(maxAllocations, roleLevelId, status, roleId);
	}

	@Override
	public JsonArray getAllRolesByEntityIdAndUserLevel(String entityIdAndUserLevel) {
		JsonArray jsonArray = new JsonArray();
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			String entityIdAndUserLevelStr = new String(decoder.decode(entityIdAndUserLevel));
			JsonObject jsonObject = new Gson().fromJson(entityIdAndUserLevelStr, JsonObject.class);
			entityIdAndUserLevel = jsonObject.get("organizationId").getAsString();
			String[] split = entityIdAndUserLevel.split("##");
			Long entityId = Long.parseLong(split[0]);
			String userLevel = split[1];
			jsonArray = getEntitySpecificRoles(entityId, userLevel);
		} catch (Exception e) {
			log.error(e);
		}
		return jsonArray;
	}

	@Override
	public JsonArray getEntitySpecificRoles(Long entityId, String userLevel) {
		JsonArray jsonArray = new JsonArray();
		List<Role> roles = roleRepository.findByDisplayOnPage(true);
		roles.forEach(role -> {
			JsonObject obj = new JsonObject();
			obj.addProperty("roleCode", role.getRoleCode());
			obj.addProperty("roleId", role.getRoleId());
			obj.addProperty("displayName", role.getDisplayName());

			jsonArray.add(obj);
		});

		List<RoleEntityMap> entityMapList = roleEntityMapRepository.findByEntityIdAndLevel(entityId, userLevel);
		if (!entityMapList.isEmpty()) {
			RoleEntityMap roleEntityMap = entityMapList.get(0);
			Role role = roleRepository.findByRoleCode(roleEntityMap.getRoleCode());
			if (role == null) {
				throw new RuntimeException("Role not found");
			}
			JsonObject obj = new JsonObject();
			obj.addProperty("roleCode", role.getRoleCode());
			obj.addProperty("roleId", role.getRoleId());
			obj.addProperty("displayName", role.getDisplayName());
			jsonArray.add(obj);
		}
		return jsonArray;
	}

	@Override
	public List<Role> getEntitySpecificRoleList(Long entityId, String userLevel) {
		// List<Role> roles = roleRepository.findAllPublicRoles();
		List<Role> roles = new ArrayList<>();
		List<RoleEntityMap> entityMapList = roleEntityMapRepository.findByEntityIdAndLevel(entityId, userLevel);
		if (!entityMapList.isEmpty()) {
			for (RoleEntityMap roleEntityMap : entityMapList) {
				Role role = roleRepository.findByRoleCode(roleEntityMap.getRoleCode());
				if (role == null) {
					throw new RuntimeException("Role not found");
				}
				roles.add(role);
			}
		}
		return roles;
	}

	@Override
	public List<Role> getEntitySpecificSelectedRoleList(Long selectedEntityId, String selectedEntityLevel) {
		List<Role> roles = new ArrayList<>();
		List<RoleEntityMap> entityMapList = roleEntityMapRepository.findByEntityIdAndLevel(selectedEntityId,
				selectedEntityLevel);
		if (!entityMapList.isEmpty()) {
			for (RoleEntityMap roleEntityMap : entityMapList) {
				Role role = roleRepository.findByRoleCode(roleEntityMap.getRoleCode());
				if (role == null) {
					throw new RuntimeException("Role not found");
				}
				roles.add(role);
			}
		}
		return roles;
	}

	@Override
	public Optional<RoleRightLevelMaster> getRoleRightLevelMasterByLevelKeyClass(String levelKeyClass) {
		return roleRightLevelMasterRepository.findByLevelKeyClass(levelKeyClass);
	}

	@Override
	public Optional<RoleLevelMap> findRoleLevelMapByRoleAndRoleLevelMapId(Long primaryRoleId, Long roleLevelId) {
		Optional<RoleLevelMap> roleLevelMap = Optional.empty();
		try {
			roleLevelMap = roleLevelMapRepository.findByRoleIdAndRoleLevelId(primaryRoleId, roleLevelId);
		} catch (Exception e) {
			log.error(e);
		}
		return roleLevelMap;
	}

	@Override
	public Optional<RoleRightLevelMaster> getRoleRightLevelMasterByCode(String objectLevel) {
		return Optional.ofNullable(roleRightLevelMasterRepository.findByLevelCode(objectLevel));
	}

	@Override
	public Optional<Role> getRoleByRoleId(Long primaryRoleId) {
		return roleRepository.findById(primaryRoleId);
	}

	@Override
	public List<RoleEntityMap> getEntitySpecificRoleEntityMapList(Long id, String levelCode) {
		return roleEntityMapRepository.findByEntityIdAndLevel(id, levelCode);
	}

	@Override
	public List<Role> getAllRollsByUser() {
		List<Role> roles = roleRepository.findAllPublicRoles();
		List<RoleEntityMap> entityMapList = roleEntityMapRepository.findByEntityIdAndLevel(
				SecurityHelper.getCurrentUserEntityId(), SecurityHelper.getCurrentUserEntityLevel());
		if (!entityMapList.isEmpty()) {
			for (RoleEntityMap roleEntityMap : entityMapList) {
				Role role = roleRepository.findByRoleCode(roleEntityMap.getRoleCode());
				if (role == null) {
					throw new RuntimeException("Role not found");
				}
				roles.add(role);
			}
		}
		return roles;
	}

	@Override
	public List<Role> getEntityOnlySpecificRoleList(long l, String s) {
		List<Role> roles = new ArrayList<>();
		try {
			List<RoleEntityMap> entityMapList = roleEntityMapRepository.findByEntityIdAndLevel(l, s);
			if (!entityMapList.isEmpty()) {
				for (RoleEntityMap roleEntityMap : entityMapList) {
					Role role = roleRepository.findByRoleCode(roleEntityMap.getRoleCode());
					if (role == null) {
						throw new RuntimeException("Role not found");
					}
					roles.add(role);
				}
			}
		} catch (Exception e) {
			log.error("Error while getting User List : ", e);
		}
		return roles;
	}

	@Override
	@Transactional
	public ServiceOutcome<List<Role>> getUpperLevelRoleListByRoleId(Long roleId) {
		ServiceOutcome<List<Role>> srvc = new ServiceOutcome<>();
		try {
			List<Role> roleList = roleRepository.getUpperLevelRoleListByRoleId(roleId);
			srvc.setData(roleList);
			srvc.setOutcome(true);

		} catch (Exception e) {
			srvc.setOutcome(false);
			srvc.setMessage(null);
			e.printStackTrace();
			srvc.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			log.error("Exception occurred in getUpperLevelRoleListByRoleId() of RoleServiceImpl => " + e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return srvc;
	}

	@Override
	@Transactional
	public ServiceOutcome<List<Role>> getLowerLevelRoleListByRoleId(Long roleId) {
		ServiceOutcome<List<Role>> srvc = new ServiceOutcome<>();
		try {
			List<Role> roleList = roleRepository.getLowerLevelRoleListByRoleId(roleId);
			srvc.setData(roleList);
			srvc.setOutcome(true);

		} catch (Exception e) {
			srvc.setOutcome(false);
			srvc.setMessage(null);
			e.printStackTrace();
			srvc.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			log.error("Exception occurred in getLowerLevelRoleListByRoleId() of RoleServiceImpl => " + e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return srvc;
	}

	@Override
	public List<String> getAllRoleCodesActiveForMobile() {
		List<String> roleCodes = new ArrayList<>();
		try {
			roleCodes = roleRepository.findByIsActiveTrueAndIsRoleApplForMobileTrue().stream().map(Role::getRoleCode)
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Error while fetching role codes applicable for mobile: ", e);
		}
		return roleCodes;
	}

	@Override
	public ServiceOutcome<List<Role>> getAllRolesActiveForMobile() {
		List<Role> rolesActiveForMobile = new ArrayList<>();
		ServiceOutcome<List<Role>> svc = new ServiceOutcome<>();
		try {
			rolesActiveForMobile = roleRepository.findByIsActiveTrueAndIsRoleApplForMobileTrue();
			svc.setOutcome(true);
			svc.setMessage("Successfully Fetched roles applicable for mobile.");
		} catch (Exception e) {
			svc.setOutcome(false);
			svc.setMessage("Error while fetching roles applicable for mobile");
			log.error("Error while fetching roles applicable for mobile: ", e);
		}
		svc.setData(rolesActiveForMobile);
		return svc;
	}
}
