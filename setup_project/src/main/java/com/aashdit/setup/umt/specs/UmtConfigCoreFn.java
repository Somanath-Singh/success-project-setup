package com.aashdit.setup.umt.specs;

import com.aashdit.setup.umt.dto.EntityHierarchy;
import com.aashdit.setup.umt.dto.NativeQueryResultsParent;
import com.aashdit.setup.umt.dto.RoleEntityCommonDto;
import com.aashdit.setup.umt.dto.RoleEntityDetails;
import com.aashdit.setup.umt.model.*;
import com.aashdit.setup.umt.repository.*;
import com.aashdit.setup.umt.service.PriviledgeService;
import com.aashdit.setup.umt.service.RoleService;
import com.aashdit.setup.umt.service.UserService;
import com.aashdit.setup.umt.utils.CommonUMTConstants;
import com.aashdit.setup.umt.utils.SecurityHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UmtConfigCoreFn {

	private final EntityManager entityManager;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private HierarchyEntityMapRepository hierarchyEntityMapRepository;
	@Autowired
	private OrganizationStructureHierarchyRepository organizationStructureHierarchyRepository;

	@Autowired
	private UmtNativeQueryRepository umtNativeQueryRepository;

	@Autowired
	private OrganizationStructureRepository organizationStructureRepository;

	@Autowired
	private PriviledgeService priviledgeService;

	@Autowired
	private RoleEntityMapRepository roleEntityMapRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	public UmtConfigCoreFn(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public static String[] addAllRoleCode(String[] roleCodes, String primaryRoleCode) {
		String[] newAllRoles = Arrays.copyOf(roleCodes, roleCodes.length + 1);
		newAllRoles[roleCodes.length] = primaryRoleCode;
		return newAllRoles;
	}

	public static Map<String, Object> updateEntityIdAndLevel(String filterData) {
		Long entityId = SecurityHelper.getCurrentUserEntityId();
		String level = SecurityHelper.getCurrentUserEntityLevel();
		if (filterData != null && !filterData.isEmpty()) {
			Base64.Decoder decoder = Base64.getDecoder();
			filterData = new String(decoder.decode(filterData));
			String[] split = filterData.split("##");
			entityId = Long.parseLong(split[0]);
			level = split[1];
		}
		Map<String, Object> map = new HashMap<>();
		map.put(CommonUMTConstants.USER_ENTITY_ID_KEY, entityId);
		map.put(CommonUMTConstants.USER_ENTITY_LEVEL_KEY, level);
		map.put(CommonUMTConstants.USER_ENTITY_ID_AND_LEVEL_KEY, entityId + "##" + level);
		return map;
	}

	private EntityHierarchy makingHierarchyEntity(NativeQueryResultsParent results, Long coId) {
		if (coId == null || coId < 1) {
			String idAndEntityCode = results.getIdAndEntityCode();
			String[] split = idAndEntityCode.split("##");
			long id = Long.parseLong(split[0]);
			String type = split[1];
			// if in hierarchyEntityMapRepository there is already parentId and parentType
			// then remove it from parentResult
//            List<HierarchyEntityMap> entityMap = hierarchyEntityMapRepository.findByParentObjectIdAndParentObjectType(id, type);
//            if (!entityMap.isEmpty()) {
//                return null;
//            }
		}
		EntityHierarchy entityHierarchy = new EntityHierarchy();
		entityHierarchy.setId(results.getId());
		entityHierarchy.setLevelType(results.getEntityCode());
		entityHierarchy.setLevelName(results.getEntityName() + " (" + results.getEntityCode() + ")");
		entityHierarchy.setLevelIdAndType(results.getId() + "##" + results.getEntityCode());
		entityHierarchy.setLevelIdAndTypeAndName(
				results.getId() + "##" + results.getEntityCode() + "##" + results.getEntityName());
		entityHierarchy.setIsSelected(results.getIsSelected());
		return entityHierarchy;
	}

	private static EntityHierarchy makingChildEntities(HierarchyEntityMap hirEntMap1) {
		EntityHierarchy entityHierarchy1 = new EntityHierarchy();
		entityHierarchy1.setId(hirEntMap1.getObjectId());
		entityHierarchy1.setLevelType(hirEntMap1.getObjectType());
		entityHierarchy1.setLevelName(hirEntMap1.getObjectName() + " (" + hirEntMap1.getObjectType() + ")");
		entityHierarchy1.setLevelIdAndType(hirEntMap1.getObjectId() + "##" + hirEntMap1.getObjectType());
		entityHierarchy1.setLevelIdAndTypeAndName(
				hirEntMap1.getObjectId() + "##" + hirEntMap1.getObjectType() + "##" + hirEntMap1.getObjectName());
		return entityHierarchy1;
	}

	public Field[] getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
		Class<?> superclass = clazz.getSuperclass();
		if (superclass != null) {
			fields.addAll(Arrays.asList(getAllFields(superclass)));
		}
		return fields.toArray(new Field[0]);
	}

	// get that field column name which is annotated with dynamic annotation passed
	// as parameter
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getParentObjectField(Class<?> clazz, Class annotation) {
		Field[] fields = getAllFields(clazz);
		for (Field field : fields) {
			if (field.isAnnotationPresent(annotation)) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					return column.name();
				}
			}
		}
		return null;
	}

	public String getTableIdName(Class<?> clazz) {
		Field[] fields = getAllFields(clazz);
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					return column.name();
				}
			}
		}
		return null;
	}

	// get class<?> from table name
	public Class<?> getClassFromTableName(String tableName) {
		try {
			// if table contains schema name then split it and get table name
			if (tableName.contains(".")) {
				tableName = tableName.split("\\.")[1];
			}
			Metamodel metamodel = entityManager.getMetamodel();
			Set<EntityType<?>> entities = metamodel.getEntities();
			for (EntityType<?> entity : entities) {
				// if entity is @Entity class and @Table name is same as tableName
				if (entity.getJavaType().isAnnotationPresent(javax.persistence.Entity.class)) {
					if (entity.getJavaType().isAnnotationPresent(javax.persistence.Table.class)) {
						javax.persistence.Table table = entity.getJavaType()
								.getAnnotation(javax.persistence.Table.class);
						if (table.name().equalsIgnoreCase(tableName)) {
							return entity.getJavaType();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Object> updateEntityIdAndLevel(Long entityId, String level, String filterData) {
		if (filterData != null && !filterData.isEmpty()) {
			Base64.Decoder decoder = Base64.getDecoder();
			filterData = new String(decoder.decode(filterData));
			String[] split = filterData.split("##");
			entityId = Long.parseLong(split[0]);
			level = split[1];
		}
		Map<String, Object> map = new HashMap<>();
		map.put(CommonUMTConstants.USER_ENTITY_ID_KEY, entityId);
		map.put(CommonUMTConstants.USER_ENTITY_LEVEL_KEY, level);
		return map;
	}

	public void addEntityIdAndLevelToModelWithData(Model model, Class<?> classObject, boolean isMandatory,
			String selectedEntityId, boolean isReadOnly, boolean isOnchangeFnNeeded) {
		try {
			model.addAttribute("organizationList", userService.getEntityIdAndUserLevelByUserId(classObject));
			model.addAttribute("isMandatory", isMandatory);
			model.addAttribute("isOnchangeFnNeeded", isOnchangeFnNeeded);
			// check model contains selectedEntityId or not
			if (model.containsAttribute(CommonUMTConstants.selectedEntityObjectIdAndType)) {
				// get selectedEntityId from a model
				if (model.getAttribute(CommonUMTConstants.selectedEntityObjectIdAndType) != null) {
					selectedEntityId = Objects
							.requireNonNull(model.getAttribute(CommonUMTConstants.selectedEntityObjectIdAndType))
							.toString();
				}
			}

			getOrgId(model, selectedEntityId);

			if (model.containsAttribute(CommonUMTConstants.isReadOnly)) {
				// get isReadOnly from a model
				if (model.getAttribute(CommonUMTConstants.isReadOnly) != null) {
					isReadOnly = (boolean) Objects.requireNonNull(model.getAttribute(CommonUMTConstants.isReadOnly));
				}
			}
			model.addAttribute(CommonUMTConstants.isReadOnly, isReadOnly);

			model.addAttribute("classObjectValue", classObject.getName());
			roleService.getRoleRightLevelMasterByLevelKeyClass(classObject.getSimpleName() + ".class")
					.ifPresent(roleRightLevelMaster -> {
						model.addAttribute("entityLevelCode", roleRightLevelMaster.getLevelCode());
					});
		} catch (Exception e) {
			log.error("Error in addEntityIdAndLevelToModelWithData", e);
		}
	}

	public void getOrgId(Model model, String selectedEntityId) {
		model.addAttribute(CommonUMTConstants.selectedEntityObjectIdAndType, selectedEntityId);
		if (selectedEntityId != null && !selectedEntityId.isEmpty()) {
			String[] split = selectedEntityId.split("##");
			long entityId = split.length > 0 ? Long.parseLong(split[0]) : 0;
			String entityLevel = split.length > 1 ? split[1] : "";
			String parentObjectIdAndType = priviledgeService.getParentObjectIdAndType(entityId, entityLevel);
			if (parentObjectIdAndType != null && !parentObjectIdAndType.isEmpty()) {
				model.addAttribute(CommonUMTConstants.selectedEntityParentObjectIdAndValue, parentObjectIdAndType);
			}
		}
	}

	public void addEntityIdAndLevelToModelForList(Model model, String filterData, Class<?> classObject) {
		try {
			model.addAttribute("entityIdAndUserLevelList", userService.getEntityIdAndUserLevelByUserId(classObject));
			if (filterData != null && !filterData.isEmpty()) {
				Base64.Decoder decoder = Base64.getDecoder();
				filterData = new String(decoder.decode(filterData));
				String[] split = filterData.split("##");
				model.addAttribute("currentCombineIdAndValue", split[0] + "##" + split[1]);
			} else {
				model.addAttribute("currentCombineIdAndValue", SecurityHelper.getCurrentUserEntityIdAndLevel());
			}
		} catch (Exception e) {
			log.error("Error in addEntityIdAndLevelToModelForList", e);
		}
	}

	public String[] otherRolesToEntity(Class<?> entityClass, Long entityId) {
		Optional<RoleRightLevelMaster> lvlMst = roleService
				.getRoleRightLevelMasterByLevelKeyClass(entityClass.getSimpleName() + ".class");
		if (lvlMst.isPresent()) {
			List<RoleEntityMap> roleList = roleService.getEntitySpecificRoleEntityMapList(entityId,
					lvlMst.get().getLevelCode());
			String[] otherRoleCodes = new String[roleList.size()];
			for (int i = 0; i < roleList.size(); i++) {
				otherRoleCodes[i] = roleList.get(i).getRoleCode();
			}
			return otherRoleCodes;
		}
		return new String[0];
	}

	public RoleEntityDetails makingRoleDetails(Long entityId, String primaryRoleCode, Class<?> entityClass) {
		RoleEntityDetails roleEntityDetails = new RoleEntityDetails();
		roleEntityDetails.setPrimaryRoleCode(primaryRoleCode);
		roleEntityDetails.setOtherRoleCodes(otherRolesToEntity(entityClass, entityId));
		return roleEntityDetails;
	}

	public RoleEntityDetails makingRoleDetails(Long entityId, Class<?> entityClass) {
		RoleEntityDetails roleEntityDetails = new RoleEntityDetails();
		roleEntityDetails.setOtherRoleCodes(otherRolesToEntity(entityClass, entityId));
		return roleEntityDetails;
	}

	public List<EntityHierarchy> getParentEntities(long entityId, String entityLevel, String entityClassStr,
			Long selectedObjectId) {
		List<EntityHierarchy> entityHierarchyList = new ArrayList<>();
		try {
			Class<?> entityClass = Class.forName(entityClassStr);
			Optional<RoleRightLevelMaster> lvlMst = roleService
					.getRoleRightLevelMasterByLevelKeyClass(entityClass.getSimpleName() + ".class");
			if (lvlMst.isPresent()) {
				List<OrganizationStructureHierarchy> hierarchyList = organizationStructureHierarchyRepository
						.findByLevelCodeAndOrgStrObjIdAndType(lvlMst.get().getLevelCode(), entityId, entityLevel);
				List<HierarchyEntityMap> removeHierarchyEntityMapAll = new ArrayList<>();
				for (OrganizationStructureHierarchy hir : hierarchyList) {
					if (hir != null) {
						List<NativeQueryResultsParent> resultsList = priviledgeService.getParentResultSet(hir);
						makingParentEntities(selectedObjectId, hir, resultsList, lvlMst.get(), entityHierarchyList);

						List<Long> objectIds = new ArrayList<>();
						if (selectedObjectId == null || selectedObjectId < 1) {
							objectIds.add(0L);
						} else {
							objectIds.add(selectedObjectId);
						}
						List<HierarchyEntityMap> removeHierarchyEntityMap = hierarchyEntityMapRepository
								.findByOrganizationStructureHierarchyEqualsAndObjectTypeEqualsAndObjectIdNotIn(hir,
										lvlMst.get().getLevelCode(), objectIds);
						removeHierarchyEntityMapAll.addAll(removeHierarchyEntityMap);
					}
				}
				// remove all parent entities which are already added in
				// hierarchyEntityMapRepository
				for (HierarchyEntityMap hirEntMap : removeHierarchyEntityMapAll) {
					entityHierarchyList.removeIf(entityHierarchy -> entityHierarchy.getLevelIdAndType()
							.equals(hirEntMap.getParentObjectIdAndType()));
				}
			}
		} catch (Exception e) {
			log.error("Error in getEntitiesByCurrentUser", e);
		}
		return entityHierarchyList;
	}

	private void makingParentEntities(Long coId, OrganizationStructureHierarchy hir,
			List<NativeQueryResultsParent> resultsList, RoleRightLevelMaster lvlMst,
			List<EntityHierarchy> entityHierarchyList) {
		for (NativeQueryResultsParent results : resultsList) {
			EntityHierarchy entityHierarchy = makingHierarchyEntity(results, coId);
			if (entityHierarchy == null) {
				continue;
			}
			entityHierarchy.setMappingType(lvlMst.getMappingType());
			String co = "";
			if (coId != null && coId > 0) {
				Optional<HierarchyEntityMap> hmp = hierarchyEntityMapRepository
						.findByOrgHirAndEntityIdAndEntityTypeAndPartIdAndType(hir, coId, lvlMst.getLevelCode(),
								results.getId(), results.getEntityCode());
				if (hmp.isPresent()) {
					co = hmp.get().getParentObjectId() + "##" + hmp.get().getParentObjectType();
				}
			}
			entityHierarchy.setIsSelected(co.equals(entityHierarchy.getLevelIdAndType()));
			entityHierarchyList.add(entityHierarchy);
		}
	}

	public List<EntityHierarchy> getChildListEntityHierarchyList(long entityId, String entityLevel,
			Long selectedObjectId) {
		List<EntityHierarchy> entityHierarchy = new ArrayList<>();
		try {
			// Class<?> entityClass = Class.forName(entityClassStr);
			Optional<RoleRightLevelMaster> lvlMst = roleService.getRoleRightLevelMasterByCode(entityLevel);
			if (lvlMst.isPresent()) {
				List<HierarchyEntityMap> allHierarchyEntityMap = new ArrayList<>();
				Optional<HierarchyEntityMap> hirEntMap = hierarchyEntityMapRepository
						.findByOrgHirAndEntityIdAndEntityType(entityId, entityLevel);
				hirEntMap.ifPresent(allHierarchyEntityMap::add);
				// recursive call to get all child entities of child entities of child entities
				// and so on
				getChildListHierarchyEntityMapList(allHierarchyEntityMap, entityLevel, entityId);
				for (HierarchyEntityMap hirEntMap1 : allHierarchyEntityMap) {
					EntityHierarchy entityHierarchy1 = makingChildEntities(hirEntMap1);
					String co = "";
					if (selectedObjectId != null && selectedObjectId > 0) {
						Optional<HierarchyEntityMap> hmp = hierarchyEntityMapRepository
								.findByOrgHirAndEntityIdAndEntityTypeAndPartIdAndType(
										hirEntMap1.getOrganizationStructureHierarchy(), selectedObjectId,
										lvlMst.get().getLevelCode(), hirEntMap1.getObjectId(),
										hirEntMap1.getObjectType());
						if (hmp.isPresent()) {
							co = hmp.get().getParentObjectId() + "##" + hmp.get().getParentObjectType();
						}
					}
					entityHierarchy1.setIsSelected(co.equals(entityHierarchy1.getLevelIdAndType()));
					entityHierarchy1.setMappingType(lvlMst.get().getMappingType());
					entityHierarchy.add(entityHierarchy1);
				}
			}

		} catch (Exception e) {
			log.error(
					"Error while fetching entity id and user level on self level UserServiceImpl::getEntityIdAndUserLevelOnSelfLevel() : ",
					e);
		}
		return entityHierarchy;
	}

	private void getChildListHierarchyEntityMapList(List<HierarchyEntityMap> allHierarchyEntityMap, String parentLevel,
			long parentId) {
		List<HierarchyEntityMap> childHierarchyEntityMapList = hierarchyEntityMapRepository
				.findByParentObjectIdAndParentObjectType(parentId, parentLevel);
		for (HierarchyEntityMap hirEntMap : childHierarchyEntityMapList) {
			Optional<RoleRightLevelMaster> lvlMst = roleService
					.getRoleRightLevelMasterByCode(hirEntMap.getObjectType());
			if (lvlMst.isPresent()) {
				if (lvlMst.get().getMappingType().equalsIgnoreCase("ONE-TO-ONE")) {

					// check if entity is already added in allHierarchyEntityMap then skip it
					boolean isAlreadyAdded = allHierarchyEntityMap.stream()
							.anyMatch(hirEntMap1 -> hirEntMap1.getObjectId().equals(hirEntMap.getObjectId())
									&& hirEntMap1.getObjectType().equals(hirEntMap.getObjectType()));
					if (!isAlreadyAdded) {
						allHierarchyEntityMap.add(hirEntMap);
						getChildListHierarchyEntityMapList(allHierarchyEntityMap, hirEntMap.getObjectType(),
								hirEntMap.getObjectId());
					}
				}
			}
		}
	}

	public void getEntitiesByCurrentUser(Model model, Boolean isTopToBottom) {
		try {
			long entityId = Optional.ofNullable(SecurityHelper.getCurrentUserEntityId()).orElse(0L);
			String entityLevel = Optional.ofNullable(SecurityHelper.getCurrentUserEntityLevel()).orElse("");
			Optional<HierarchyEntityMap> hirEntMap = hierarchyEntityMapRepository
					.findByOrgHirAndEntityIdAndEntityType(entityId, entityLevel);
			if (hirEntMap.isPresent()) {
				OrganizationStructureHierarchy hierarchy = hirEntMap.get().getOrganizationStructureHierarchy();
				List<OrganizationStructureHierarchy> hierarchyList = new ArrayList<>();
				if (isTopToBottom) {
					hierarchyList = organizationStructureHierarchyRepository.findByParentHieIdId(hierarchy.getId());
				} else {
					Optional<OrganizationStructureHierarchy> parentHierarchy = organizationStructureHierarchyRepository
							.findById(hierarchy.getParentHierarchyId());
					if (parentHierarchy.isPresent()) {
						hierarchyList.add(parentHierarchy.get());
					}
				}
				List<EntityHierarchy> entityHierarchyList = new ArrayList<>();
				for (OrganizationStructureHierarchy orgHir : hierarchyList) {
					List<HierarchyEntityMap> entityMapList = hierarchyEntityMapRepository
							.findByOrganizationStructureHierarchy(orgHir);
					for (HierarchyEntityMap entityMap : entityMapList) {
						EntityHierarchy entityHierarchy = new EntityHierarchy();
						entityHierarchy.setId(entityMap.getObjectId());
						entityHierarchy.setLevelType(entityMap.getObjectType());
						entityHierarchy
								.setLevelName(entityMap.getObjectName() + " (" + entityMap.getObjectType() + ")");
						entityHierarchy.setLevelIdAndType(entityMap.getObjectId() + "##" + entityMap.getObjectType());
						entityHierarchyList.add(entityHierarchy);
					}
				}
				model.addAttribute("entityHierarchyList", entityHierarchyList);
			}

		} catch (Exception e) {
			log.error("Error in getEntitiesByCurrentUser", e);
		}
	}

	public void getEntitiesByCurrentUser(long entityId, String entityLevel, Model model, Boolean isTopToBottom) {
		List<EntityHierarchy> entityHierarchyList = new ArrayList<>();
		try {
			Optional<HierarchyEntityMap> hirEntMap = hierarchyEntityMapRepository
					.findByOrgHirAndEntityIdAndEntityType(entityId, entityLevel);
			if (hirEntMap.isPresent()) {
				OrganizationStructureHierarchy hierarchy = hirEntMap.get().getOrganizationStructureHierarchy();
				List<OrganizationStructureHierarchy> hierarchyList = new ArrayList<>();
				if (isTopToBottom) {
					hierarchyList = organizationStructureHierarchyRepository.findByParentHieIdId(hierarchy.getId());
				} else {
					Optional<OrganizationStructureHierarchy> parentHierarchy = organizationStructureHierarchyRepository
							.findById(hierarchy.getParentHierarchyId());
					if (parentHierarchy.isPresent()) {
						hierarchyList.add(parentHierarchy.get());
					}
				}
				for (OrganizationStructureHierarchy orgHir : hierarchyList) {
					List<HierarchyEntityMap> entityMapList = hierarchyEntityMapRepository
							.findByOrganizationStructureHierarchy(orgHir);
					for (HierarchyEntityMap entityMap : entityMapList) {
						EntityHierarchy entityHierarchy = new EntityHierarchy();
						entityHierarchy.setId(entityMap.getObjectId());
						entityHierarchy.setLevelType(entityMap.getObjectType());
						entityHierarchy
								.setLevelName(entityMap.getObjectName() + " (" + entityMap.getObjectType() + ")");
						entityHierarchy.setLevelIdAndType(entityMap.getObjectId() + "##" + entityMap.getObjectType());
						entityHierarchyList.add(entityHierarchy);
					}
				}

			}
		} catch (Exception e) {
			log.error("Error in getEntitiesByCurrentUser", e);
		}
		model.addAttribute("entityHierarchyList", entityHierarchyList);
	}

	public List<EntityHierarchy> getEntitiesByCurrentUser(long entityId, String entityLevel, Boolean isTopToBottom) {
		List<EntityHierarchy> entityHierarchyList = new ArrayList<>();
		try {
			Optional<HierarchyEntityMap> hirEntMap = hierarchyEntityMapRepository
					.findByOrgHirAndEntityIdAndEntityType(entityId, entityLevel);
			if (hirEntMap.isPresent()) {
				OrganizationStructureHierarchy hierarchy = hirEntMap.get().getOrganizationStructureHierarchy();
				List<OrganizationStructureHierarchy> hierarchyList = new ArrayList<>();
				if (isTopToBottom) {
					hierarchyList = organizationStructureHierarchyRepository.findByParentHieIdId(hierarchy.getId());
				} else {
					Optional<OrganizationStructureHierarchy> parentHierarchy = organizationStructureHierarchyRepository
							.findById(hierarchy.getParentHierarchyId());
					if (parentHierarchy.isPresent()) {
						hierarchyList.add(parentHierarchy.get());
					}
				}
				for (OrganizationStructureHierarchy orgHir : hierarchyList) {
					List<HierarchyEntityMap> entityMapList = hierarchyEntityMapRepository
							.findByOrganizationStructureHierarchy(orgHir);
					for (HierarchyEntityMap entityMap : entityMapList) {
						EntityHierarchy entityHierarchy = new EntityHierarchy();
						entityHierarchy.setId(entityMap.getObjectId());
						entityHierarchy.setLevelType(entityMap.getObjectType());
						entityHierarchy
								.setLevelName(entityMap.getObjectName() + " (" + entityMap.getObjectType() + ")");
						entityHierarchy.setLevelIdAndType(entityMap.getObjectId() + "##" + entityMap.getObjectType());
						entityHierarchyList.add(entityHierarchy);
					}
				}

			}
		} catch (Exception e) {
			log.error("Error in getEntitiesByCurrentUser", e);
		}
		return entityHierarchyList;
	}

	public void addObjectIdAndTypeToModelWithData(Model model, String upperEntityIdAndType) {
		try {
			if (upperEntityIdAndType != null && !upperEntityIdAndType.isEmpty()) {
				model.addAttribute(CommonUMTConstants.isReadOnly, true);
				model.addAttribute(CommonUMTConstants.selectedUpperEntityObjectIdAndType, upperEntityIdAndType);
				String[] split = upperEntityIdAndType.split("##");
				long entityId = Long.parseLong(split[0]);
				String entityLevel = split[1];
				String objectIdAndType = priviledgeService.getObjectIdAndType(entityId, entityLevel);
				if (objectIdAndType != null && !objectIdAndType.isEmpty()) {
					model.addAttribute(CommonUMTConstants.selectedOrgEntityObjectIdAndType, objectIdAndType);
				}
			}
		} catch (Exception e) {
			log.error("Error in addObjectIdAndTypeToModelWithData", e);
		}
	}

	public void addObjectIdAndTypeToModelWithDataRedAtr(RedirectAttributes model, String upperEntityIdAndType) {
		try {
			if (upperEntityIdAndType != null && !upperEntityIdAndType.isEmpty()) {
				model.addFlashAttribute(CommonUMTConstants.isReadOnly, true);
				model.addFlashAttribute(CommonUMTConstants.selectedUpperEntityObjectIdAndType, upperEntityIdAndType);
				String[] split = upperEntityIdAndType.split("##");
				long entityId = Long.parseLong(split[0]);
				String entityLevel = split[1];
				String objectIdAndType = priviledgeService.getObjectIdAndType(entityId, entityLevel);
				if (objectIdAndType != null && !objectIdAndType.isEmpty()) {
					model.addFlashAttribute(CommonUMTConstants.selectedOrgEntityObjectIdAndType, objectIdAndType);
				}
			}
		} catch (Exception e) {
			log.error("Error in addObjectIdAndTypeToModelWithData", e);
		}
	}

	public List<RoleEntityCommonDto> getRoleListByEntityId(long entityIdLong, String entityLevel) {
		// get All Public Role List
		List<RoleEntityMap> roleList = roleEntityMapRepository.findByEntityIdAndEntityLevel(entityIdLong, entityLevel);

		List<RoleEntityCommonDto> result = new ArrayList<>();

		for (RoleEntityMap roleEntityMap : roleList) {

			String roleCode = roleEntityMap.getRoleCode();

			Role role = roleRepository.findByRoleCode(roleCode);

			RoleEntityCommonDto dto = new RoleEntityCommonDto();

			dto.setRoleId(role.getRoleId());
			dto.setRoleCode(roleCode);
			dto.setEntityLevel(entityLevel);

			String combineValue = role.getDisplayName() + " || " + roleCode + " || " + entityLevel;
			dto.setCombineValue(combineValue);

			dto.setDisplayName(role.getDisplayName());

			result.add(dto);
		}
		/*
		 * List<Role> publicList =
		 * roleRepository.findByRoleAccessType(CommonUMTConstants.
		 * ROLE_ACCESS_TYPE_PUBLIC); for (Role role : publicList) { RoleEntityCommonDto
		 * dto = new RoleEntityCommonDto(); dto.setRoleId(role.getRoleId());
		 * dto.setRoleCode(role.getRoleCode()); dto.setEntityLevel(entityLevel); String
		 * combineValue = role.getDisplayName() + " || " + role.getRoleCode() + " || " +
		 * entityLevel; dto.setCombineValue(combineValue);
		 * dto.setDisplayName(role.getDisplayName()); result.add(dto); }
		 */
		Set<RoleEntityCommonDto> set = result.stream().collect(
				Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RoleEntityCommonDto::getRoleId))));
		return set.stream().sorted(Comparator.comparing(RoleEntityCommonDto::getRoleId)).collect(Collectors.toList());
	}

}
