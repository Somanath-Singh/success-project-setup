package com.aashdit.setup.umt.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.dto.NativeQueryResults;
import com.aashdit.setup.umt.dto.NativeQueryResultsParent;
import com.aashdit.setup.umt.dto.OrganizationStructureParentChild;
import com.aashdit.setup.umt.model.HierarchyEntityMap;
import com.aashdit.setup.umt.model.OrganizationStructure;
import com.aashdit.setup.umt.model.OrganizationStructureHierarchy;
import com.aashdit.setup.umt.model.Privilege;
import com.aashdit.setup.umt.model.RoleMenuMap;
import com.aashdit.setup.umt.model.RoleMenuPrivilegeMap;
import com.aashdit.setup.umt.model.RoleRightLevelMaster;
import com.aashdit.setup.umt.model.UmtNativeQuery;
import com.aashdit.setup.umt.model.User;
import com.aashdit.setup.umt.repository.HierarchyEntityMapMultipleRepository;
import com.aashdit.setup.umt.repository.HierarchyEntityMapRepository;
import com.aashdit.setup.umt.repository.OrganizationStructureHierarchyRepository;
import com.aashdit.setup.umt.repository.OrganizationStructureRepository;
import com.aashdit.setup.umt.repository.PriviledgeRepository;
import com.aashdit.setup.umt.repository.RoleMenuMapRepository;
import com.aashdit.setup.umt.repository.RoleMenuPrivilegeMapRepository;
import com.aashdit.setup.umt.repository.RoleRightLevelMasterRepository;
import com.aashdit.setup.umt.repository.UmtNativeQueryRepository;
import com.aashdit.setup.umt.specs.ObjectEntityId;
import com.aashdit.setup.umt.specs.ObjectEntityLevel;
import com.aashdit.setup.umt.specs.UmtConfigCoreFn;
import com.aashdit.setup.umt.utils.CommonHelperFunctions;
import com.aashdit.setup.umt.utils.CommonUMTConstants;
import com.aashdit.setup.umt.utils.SecurityHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class PriviledgeServiceImpl implements PriviledgeService, MessageSourceAware {

	private final Logger log = Logger.getLogger(this.getClass());

	private MessageSource messageSource;
	
	@Autowired
	private PriviledgeRepository priviledgeRepository;
	
	@Autowired
	private RoleMenuMapRepository rmmRepository;
	
	@Autowired
	private RoleMenuPrivilegeMapRepository rmpMapRepository;
	
	@Autowired
	private RoleRightLevelMasterRepository roleRightLevelMasterRepository;
	
	@Autowired
	private OrganizationStructureRepository organizationStructureRepository;

	@Autowired
	private HierarchyEntityMapRepository hierarchyEntityMapRepository;

	@SuppressWarnings("unused")
	@Autowired
	private HierarchyEntityMapMultipleRepository hierarchyEntityMapMultipleRepository;
	
	@Autowired
	private OrganizationStructureHierarchyRepository organizationStructureHierarchyRepository;
	
	@Autowired
	private UmtNativeQueryRepository umtNativeQueryRepository;
	
	@Autowired
	private UmtConfigCoreFn umtConfigCoreFn;

	@PersistenceContext
	private final EntityManager entityManager;

	public PriviledgeServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private static NativeQueryResultsParent makingParentNativeObject(HierarchyEntityMap entityMap, String mappingType) {
		NativeQueryResultsParent nativeQueryResult = new NativeQueryResultsParent();
		nativeQueryResult.setId(entityMap.getObjectId());
		nativeQueryResult.setEntityName(entityMap.getObjectName());
		nativeQueryResult.setEntityCode(entityMap.getObjectType());
		nativeQueryResult.setParentEntityId(entityMap.getParentObjectId());
		nativeQueryResult.setParentEntityCode(entityMap.getParentObjectType());
		nativeQueryResult.setIdAndEntityCode(nativeQueryResult.getId() + "##" + nativeQueryResult.getEntityCode());
		nativeQueryResult.setParentEntityIdAndCode(
				nativeQueryResult.getParentEntityId() + "##" + nativeQueryResult.getParentEntityCode());
		nativeQueryResult.setMappingType(mappingType);
		return nativeQueryResult;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;

	}

	@Override
	public ServiceOutcome<List<Privilege>> findAllPriviledges(Boolean includeInActive) {
		ServiceOutcome<List<Privilege>> svcOutcome = new ServiceOutcome<>();

		try {
			List<Privilege> lstPriviledges = null;
			if (includeInActive) {
				lstPriviledges = priviledgeRepository.findAll();
			} else {
				lstPriviledges = priviledgeRepository.findByIsActive(true);
			}

			svcOutcome.setData(lstPriviledges);
		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Privilege> findById(Long privilegeId) {
		ServiceOutcome<Privilege> svcOutcome = new ServiceOutcome<>();

		try {
			Privilege priviledge = priviledgeRepository.getOne(privilegeId);

			svcOutcome.setData(priviledge);
		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Privilege> savePrivilege(Privilege privilege) {
		ServiceOutcome<Privilege> svcOutcome = new ServiceOutcome<Privilege>();
		try {
			User user = SecurityHelper.getCurrentUser();

			if (privilege.getId() == null) // INSERT
			{
				privilege.setIsActive(privilege.getIsActive() != null);

				privilege.setCreatedBy(user.getUserId());
				privilege.setCreatedOn(new Date());

				privilege = priviledgeRepository.save(privilege);
				svcOutcome.setData(privilege);
			} else // UPDATE
			{
				Privilege dbPrivilege = priviledgeRepository.getOne(privilege.getId());
				dbPrivilege.setCode(privilege.getCode());
				dbPrivilege.setIsActive(privilege.getIsActive() != null);
				dbPrivilege.setDesc(privilege.getDesc());
				dbPrivilege.setPriviledgeType(privilege.getPriviledgeType());
				dbPrivilege.setUiLabelCode(privilege.getUiLabelCode());

				dbPrivilege.setUpdateBy(user.getUserId());
				dbPrivilege.setUpdatedOn(new Date());

				dbPrivilege = priviledgeRepository.save(dbPrivilege);
				svcOutcome.setData(dbPrivilege);
			}

			svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));

		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<String> assignPrivilege(Long roleId, Long menuId, Long privId) {
		ServiceOutcome<String> svcOutcome = new ServiceOutcome<String>();
		try {
			User user = SecurityHelper.getCurrentUser();
			RoleMenuMap rmm = rmmRepository.findByRoleIdAndMenuId(roleId, menuId);
			if (rmm != null) {
				RoleMenuPrivilegeMap rmpMap = rmpMapRepository.findByRoleMenuIdAndPrivilegeId(rmm.getRoleMenuId(),
						privId);
				if (rmpMap == null) {
					rmpMap = new RoleMenuPrivilegeMap();
					rmpMap.setCreatedBy(user.getUserId());
					rmpMap.setCreatedOn(new Date());
					rmpMap.setRoleMenuId(rmm.getRoleMenuId());
					rmpMap.setPrivilegeId(privId);
					rmpMap.setIsActive(true);
				} else {
					rmpMap.setIsActive(true);
					rmpMap.setUpdateBy(user.getUserId());
					rmpMap.setUpdatedOn(new Date());
				}
				rmpMapRepository.save(rmpMap);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
				svcOutcome.setData(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
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
	public ServiceOutcome<String> revokePrivilege(Long roleId, Long menuId, Long privId) {
		ServiceOutcome<String> svcOutcome = new ServiceOutcome<String>();
		try {
			User user = SecurityHelper.getCurrentUser();
			RoleMenuMap rmm = rmmRepository.findByRoleIdAndMenuId(roleId, menuId);
			if (rmm != null) {
				RoleMenuPrivilegeMap rmpMap = rmpMapRepository.findByRoleMenuIdAndPrivilegeId(rmm.getRoleMenuId(),
						privId);
				if (rmpMap != null) {
					rmpMap.setIsActive(false);
					rmpMap.setUpdateBy(user.getUserId());
					rmpMap.setUpdatedOn(new Date());
				}
				rmpMapRepository.save(rmpMap);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
				svcOutcome.setData(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
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
	public List<RoleRightLevelMaster> getOrganizationLevelList() {
		List<RoleRightLevelMaster> roleRightLevelMasters = new ArrayList<>();
		try {
			roleRightLevelMasters = roleRightLevelMasterRepository.getOrganizationLevelListByDisplayAtConfig(true);
		} catch (Exception e) {
			log.error("Error in getOrganizationLevelList", e);
		}
		return roleRightLevelMasters;
	}

	@Transactional
	@Override
	public boolean saveOrganizationStructure(String organizationStructureArr) {
		try {
			if (organizationStructureArr == null || organizationStructureArr.isEmpty()) {
				return false;
			}
			Base64.Decoder decoder = Base64.getDecoder();
			String decode = new String(decoder.decode(organizationStructureArr));
			JsonObject comingObject = new Gson().fromJson(decode, JsonObject.class);
			String forObjectIdAndTypeId = comingObject.get("forObjectIdAndTypeId").getAsString();

			OrganizationStructure orgStr = new OrganizationStructure();
			String[] split = forObjectIdAndTypeId.split("##");
			long entityId = Long.parseLong(split[0]);
			String level = split[1];
			Optional<OrganizationStructure> orgStrExt = organizationStructureRepository
					.findByObjectIdAndObjectType(entityId, level);
			if (orgStrExt.isPresent()) {
				orgStr = orgStrExt.get();
			}
			orgStr.setObjectId(entityId);
			orgStr.setObjectType(level);
			orgStr = organizationStructureRepository.save(orgStr);

			JsonArray jsonArray = comingObject.getAsJsonArray("organizationStructure");
			for (int i = 0; i < jsonArray.size(); i++) {
				// each object contain value = String,
				// children = JsonArray as same as parent now create a recursive function
				// to save the data in db with parent id as parent id
				JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
				saveOrgStrHierarchy(jsonObject, orgStr.getId(), null);
			}
			return true;
		} catch (Exception e) {
			log.error("Error in saveOrganizationStructure", e);
			return false;
		}
	}

	private void saveOrgStrHierarchy(JsonObject jsonObject, Long id, Long parentId) {
		OrganizationStructureHierarchy orgStrHierarchy = new OrganizationStructureHierarchy();
		String hrgIdStr = jsonObject.get("hrgId") == null ? null : jsonObject.get("hrgId").getAsString();
		Long hrgId = null;
		if (hrgIdStr != null && !hrgIdStr.isEmpty()) {
			hrgId = Long.parseLong(hrgIdStr);
		}
		if (hrgId != null && hrgId > 0) {
			orgStrHierarchy = organizationStructureHierarchyRepository.getOne(hrgId);
		}
		orgStrHierarchy.setOrganizationStructure(organizationStructureRepository.getOne(id));
		orgStrHierarchy.setLevelCode(jsonObject.get("value").getAsString());
		orgStrHierarchy.setParentHierarchyId(parentId);
		orgStrHierarchy.setLevelHeight(jsonObject.get("levelHeight").getAsInt());
		orgStrHierarchy = organizationStructureHierarchyRepository.save(orgStrHierarchy);
		if (parentId == null) {
			HierarchyEntityMap hierarchyEntityMap = new HierarchyEntityMap();
			boolean parentPresent = false;
			long orgNameId = jsonObject.get("orgNameId").getAsLong();
			String orgName = jsonObject.get("orgName").getAsString();
			Optional<HierarchyEntityMap> hierarchy = hierarchyEntityMapRepository
					.findByOrgHirAndEntityIdAndEntityType(orgStrHierarchy, orgNameId, orgStrHierarchy.getLevelCode());
			if (hierarchy.isPresent()) {
				hierarchyEntityMap = hierarchy.get();
				parentPresent = true;
			}
			Long munId = orgStrHierarchy.getOrganizationStructure().getObjectId();
			String munLevel = orgStrHierarchy.getOrganizationStructure().getObjectType();
			String munName = parentPresent ? hierarchyEntityMap.getObjectName() : orgName;

			hierarchyEntityMap.setObjectId(munId);
			hierarchyEntityMap.setObjectType(munLevel);
			hierarchyEntityMap.setObjectName(munName);
			Long parentEntityId = parentPresent ? hierarchyEntityMap.getParentObjectId() : munId;
			String parentEntityType = parentPresent ? hierarchyEntityMap.getParentObjectType() : munLevel;
			String parentEntityName = parentPresent ? hierarchyEntityMap.getParentObjectName() : munName;
			/*
			 * try { RoleRightLevelMaster byLevelCode =
			 * roleRightLevelMasterRepository.findByLevelCode(munLevel); String tableName =
			 * byLevelCode.getLevelEntityName(); // className is like MstMunicipality.class
			 * convert it to MstMunicipality class Class<?> clazz =
			 * umtConfigCoreFn.getClassFromTableName(tableName); String parentEntityTypeStr
			 * = umtConfigCoreFn.getParentObjectField(clazz, ParentObjectLevel.class);
			 * String parentEntityIdStr = umtConfigCoreFn.getParentObjectField(clazz,
			 * ParentObjectId.class);
			 * 
			 * if (parentEntityType != null && parentEntityId != null) { parentEntityId =
			 * Long.parseLong(parentEntityIdStr); parentEntityType = parentEntityTypeStr; }
			 * }catch (Exception e){ log.error("Error in saveOrgStrHierarchy", e); }
			 */

			hierarchyEntityMap.setParentObjectId(parentEntityId);
			hierarchyEntityMap.setParentObjectType(parentEntityType);
			hierarchyEntityMap.setParentObjectName(parentEntityName);
			hierarchyEntityMap.setOrganizationStructureHierarchy(orgStrHierarchy);
			hierarchyEntityMapRepository.save(hierarchyEntityMap);
		}
		JsonArray children = jsonObject.getAsJsonArray("children");
		for (int i = 0; i < children.size(); i++) {
			saveOrgStrHierarchy(children.get(i).getAsJsonObject(), id, orgStrHierarchy.getId());
		}
	}

	@Override
	public List<OrganizationStructureParentChild> viewOrganizationStructure(String filterData) throws Exception {
		List<OrganizationStructureParentChild> orgStrHierarchyList = new ArrayList<>();
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			String decode = new String(decoder.decode(filterData));
			String[] split = decode.split("##");
			Long id = Long.parseLong(split[0]);
			String type = split[1];
			Optional<OrganizationStructure> orgStr = organizationStructureRepository.findByObjectIdAndObjectType(id,
					type);
			if (!orgStr.isPresent()) {
				return orgStrHierarchyList;
			}
			viewOrgStrHierarchy(orgStr.get().getId(), null, orgStrHierarchyList);
		} catch (Exception e) {
			log.error("Error in viewOrganizationStructure", e);
			throw new Exception("Error in viewOrganizationStructure");
		}
		return orgStrHierarchyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NativeQueryResults> getEntitySpecificEntity(String entityIdAndTypeEncode, String levelCode,
			String orgIdEncode, String levelHeight) {
		List<NativeQueryResults> nativeQueryResults = new ArrayList<>();
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			String decode = new String(decoder.decode(entityIdAndTypeEncode));
			String[] split = decode.split("##");
			long id = Long.parseLong(split[0]);
			String type = split[1];

			levelCode = new String(decoder.decode(levelCode));
			String orgIdEncodeStr = new String(decoder.decode(orgIdEncode));
			Long orgId = orgIdEncodeStr.isEmpty() ? 0L : Long.parseLong(orgIdEncodeStr);
			Optional<OrganizationStructureHierarchy> orgStrHierarchy = organizationStructureHierarchyRepository
					.findById(orgId);
			if (!orgStrHierarchy.isPresent()) {
				return nativeQueryResults;
			}
			List<NativeQueryResultsParent> parentResult = this.getParentResultSet(orgStrHierarchy.get());

			Optional<UmtNativeQuery> nativeQuery = umtNativeQueryRepository
					.findByQueryCode(levelCode + CommonUMTConstants.OBJECT_ID_AND_TYPE);
			if (!nativeQuery.isPresent()) {
				return nativeQueryResults;
			}

			String sql = nativeQuery.get().getQuery();
			sql = sql.replace(":objectId", Long.toString(id));
			sql = sql.replace(":objectType", "'" + type + "'");

			Integer levelHeightInt = Integer.parseInt(CommonHelperFunctions.decodeBase64(levelHeight));
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> resultList = query.getResultList();
			for (Object[] objects : resultList) {
				// get 0 and 2 index value
				long entityId = objects.length > 0 && objects[0] != null ? ((BigInteger) objects[0]).longValue() : 0;
				String entityLevel = objects.length > 2 && objects[2] != null ? objects[2].toString() : null;
				Optional<HierarchyEntityMap> entityMap = hierarchyEntityMapRepository
						.findByOrgHirAndEntityIdAndEntityType(entityId, entityLevel);
				boolean shouldAdd = true;
				if (entityMap.isPresent()) {
					OrganizationStructureHierarchy hierarchy = entityMap.get().getOrganizationStructureHierarchy();
					if (hierarchy != null) {
						if (!Objects.equals(hierarchy.getLevelHeight(), levelHeightInt)) {
							shouldAdd = false;
						}

					}
				}
				if (shouldAdd) {
					nativeQueryResults.add(makingNativeQueryResult(objects, parentResult, orgStrHierarchy.get()));
				}
			}

		} catch (Exception e) {
			log.error("Error in getEntitySpecificEntity", e);
		}
		return nativeQueryResults;
	}

	public List<NativeQueryResultsParent> getParentResultSet(OrganizationStructureHierarchy orgStrHierarchy) {
		List<NativeQueryResultsParent> parentResult = new ArrayList<>();
		RoleRightLevelMaster lvlMst = roleRightLevelMasterRepository.findByLevelCode(orgStrHierarchy.getLevelCode());
		if (lvlMst != null) {
			String mappingType = lvlMst.getMappingType();
			OrganizationStructureHierarchy parentHierarchy = orgStrHierarchy.getParentHierarchyId() == null
					? orgStrHierarchy
					: organizationStructureHierarchyRepository.getOne(orgStrHierarchy.getParentHierarchyId());
			List<HierarchyEntityMap> entityMaps = hierarchyEntityMapRepository
					.findByOrganizationStructureHierarchy(parentHierarchy);
			for (HierarchyEntityMap entityMap : entityMaps) {
				NativeQueryResultsParent nativeQueryResult = makingParentNativeObject(entityMap, mappingType);
				parentResult.add(nativeQueryResult);
			}
		}
		return parentResult;
	}

	public List<NativeQueryResultsParent> getChildResultSet(OrganizationStructureHierarchy orgStrHierarchy) {
		List<NativeQueryResultsParent> parentResult = new ArrayList<>();
		RoleRightLevelMaster lvlMst = roleRightLevelMasterRepository.findByLevelCode(orgStrHierarchy.getLevelCode());
		if (lvlMst != null) {
			@SuppressWarnings("unused")
			String mappingType = lvlMst.getMappingType();
			// OrganizationStructureHierarchy childHierarchy =
			// orgStrHierarchy.getChildHierarchyId() == null ? orgStrHierarchy :
			// organizationStructureHierarchyRepository.getOne(orgStrHierarchy.getChildHierarchyId());
		}
		return parentResult;
	}

	@Override
	public String getParentObjectIdAndType(long entityId, String entityLevel) {
		String parentObjectIdAndType = "";
		try {
			Optional<HierarchyEntityMap> entityMap = hierarchyEntityMapRepository
					.findByOrgHirAndEntityIdAndEntityType(entityId, entityLevel);
			if (entityMap.isPresent()) {
				parentObjectIdAndType = entityMap.get().getParentObjectId() + "##"
						+ entityMap.get().getParentObjectType();
			}
		} catch (Exception e) {
			log.error("Error in getParentObjectIdAndType", e);
		}
		return parentObjectIdAndType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getObjectIdAndType(long entityId, String entityLevel) {
		String objectIdAndType = "";
		try {
			RoleRightLevelMaster lvlMst = roleRightLevelMasterRepository.findByLevelCode(entityLevel);
			if (lvlMst != null) {
				String tableName = lvlMst.getLevelEntityName();
				Class<?> clazz = umtConfigCoreFn.getClassFromTableName(tableName);
				String parentEntityLevelStr = umtConfigCoreFn.getParentObjectField(clazz, ObjectEntityLevel.class);
				String parentEntityIdStr = umtConfigCoreFn.getParentObjectField(clazz, ObjectEntityId.class);
				String id = umtConfigCoreFn.getTableIdName(clazz);
				if (parentEntityLevelStr != null && parentEntityIdStr != null) {
					String sql = "SELECT " + parentEntityIdStr + ", " + parentEntityLevelStr + " FROM " + tableName
							+ " WHERE " + id + " = " + entityId;
					Query query = entityManager.createNativeQuery(sql);
					List<Object[]> resultList = query.getResultList();
					for (Object[] objects : resultList) {
						String parentEntityId = objects[0] != null ? objects[0].toString() : null;
						String parentEntityType = objects[1] != null ? objects[1].toString() : null;
						if (parentEntityId != null && parentEntityType != null) {
							objectIdAndType = parentEntityId + "##" + parentEntityType;
						}
					}
				} else {
					objectIdAndType = entityId + "##" + entityLevel;
				}
			}
		} catch (Exception e) {
			log.error("Error in getObjectIdAndType", e);
		}
		return objectIdAndType;
	}

	@Override
	public JsonObject getHierarchyView(Long entityId, String entityLevel) {
		JsonObject JsonObject = new JsonObject();
		try {
			JsonObject parentHierarchyView = getParentHierarchyView(entityId, entityLevel, true);
			JsonObject.add("parent", parentHierarchyView);
			JsonObject childHierarchyView = getChildHierarchyView(entityId, entityLevel);
			JsonObject.add("child", childHierarchyView);
		} catch (Exception e) {
			log.error("Error in getHierarchyView", e);
		}
		return JsonObject;
	}

	private JsonObject getChildHierarchyView(Long parentId, String parentType) {
		JsonObject jsonObject = new JsonObject();
		try {
			List<HierarchyEntityMap> child = hierarchyEntityMapRepository
					.findByParentObjectIdAndParentObjectType(parentId, parentType);
			JsonArray jsonArray = new JsonArray();
			for (HierarchyEntityMap entityMap : child) {
				JsonObject childHierarchyView = getChildHierarchyView(entityMap.getObjectId(),
						entityMap.getObjectType());
				JsonObject jsonObject1 = new JsonObject();
				jsonObject1.addProperty("name", entityMap.getObjectName());
				jsonObject1.add("child", childHierarchyView);
				jsonObject1.addProperty("levelCode", entityMap.getObjectType());
				jsonArray.add(jsonObject1);
			}
			jsonObject.add("child", jsonArray);
		} catch (Exception e) {
			log.error("Error in getChildHierarchyView", e);
		}
		return jsonObject;
	}

	private JsonObject getParentHierarchyView(long entityId, String entityType, boolean isParticularEntity) {
		JsonObject jsonObject = new JsonObject();
		try {
			Optional<HierarchyEntityMap> hierarchyEntityMap = hierarchyEntityMapRepository
					.findByEntityIdAndEntityType(entityId, entityType);
			if (hierarchyEntityMap.isPresent()) {
				long parentObjectId = hierarchyEntityMap.get().getParentObjectId();
				String parentObjectType = hierarchyEntityMap.get().getParentObjectType();

				long objectId = hierarchyEntityMap.get().getObjectId();
				String objectType = hierarchyEntityMap.get().getObjectType();
				jsonObject.addProperty("isParticularEntity", isParticularEntity);
				// if objectId, objectType and parentObjectId, parentObjectType are same then
				// return
				if (objectId == parentObjectId && objectType.equals(parentObjectType)) {
					jsonObject.addProperty("name", hierarchyEntityMap.get().getObjectName());
					jsonObject.add("parent", new JsonArray());
					jsonObject.addProperty("levelCode", hierarchyEntityMap.get().getObjectType());
					return jsonObject;
				}
				jsonObject.addProperty("name", hierarchyEntityMap.get().getObjectName());
				JsonObject parent = getParentHierarchyView(parentObjectId, parentObjectType, false);
				jsonObject.add("parent", parent);
				jsonObject.addProperty("levelCode", hierarchyEntityMap.get().getObjectType());
			}
		} catch (Exception e) {
			log.error("Error in getParentHierarchyView", e);
		}
		return jsonObject;
	}

	@SuppressWarnings("unused")
	private NativeQueryResultsParent makingNativeQueryResultParent(Object[] objects) {
		NativeQueryResultsParent nativeQueryResult = new NativeQueryResultsParent();
		nativeQueryResult
				.setId(objects.length > 0 && objects[0] != null ? ((BigInteger) objects[0]).longValue() : null);
		nativeQueryResult.setEntityName(objects.length > 1 && objects[1] != null ? objects[1].toString() : null);
		nativeQueryResult.setEntityCode(objects.length > 2 && objects[2] != null ? objects[2].toString() : null);
		nativeQueryResult.setParentEntityId(
				objects.length > 3 && objects[3] != null ? ((BigInteger) objects[3]).longValue() : null);
		nativeQueryResult.setParentEntityCode(objects.length > 4 && objects[4] != null ? objects[4].toString() : null);
		nativeQueryResult.setIdAndEntityCode(nativeQueryResult.getId() + "##" + nativeQueryResult.getEntityCode());
		nativeQueryResult.setParentEntityIdAndCode(
				nativeQueryResult.getParentEntityId() + "##" + nativeQueryResult.getParentEntityCode());
		return nativeQueryResult;
	}

	@SuppressWarnings("unused")
	private String getEntityIdsForHierarchy(OrganizationStructureHierarchy parentHierarchy) {
		List<HierarchyEntityMap> entityMaps = hierarchyEntityMapRepository
				.findByOrganizationStructureHierarchy(parentHierarchy);
		return entityMaps.stream().map(HierarchyEntityMap::getObjectId).map(String::valueOf).map(s -> "'" + s + "'")
				.collect(Collectors.joining(","));
	}

	private NativeQueryResults makingNativeQueryResult(Object[] objects, List<NativeQueryResultsParent> parentResult,
			OrganizationStructureHierarchy hirChy) {
		NativeQueryResults nativeQueryResult = new NativeQueryResults();
		nativeQueryResult
				.setId(objects.length > 0 && objects[0] != null ? ((BigInteger) objects[0]).longValue() : null);
		nativeQueryResult.setEntityName(objects.length > 1 && objects[1] != null ? objects[1].toString() : null);
		nativeQueryResult.setEntityCode(objects.length > 2 && objects[2] != null ? objects[2].toString() : null);
		nativeQueryResult.setIdAndEntityCode(nativeQueryResult.getId() + "##" + nativeQueryResult.getEntityCode());

		hierarchyEntityMapRepository
				.findByOrgHirAndEntityIdAndEntityType(hirChy, nativeQueryResult.getId(), hirChy.getLevelCode())
				.ifPresent(entityMap -> {
					nativeQueryResult.setParentEntityId(entityMap.getParentObjectId());
					nativeQueryResult.setParentEntityCode(entityMap.getParentObjectType());
					nativeQueryResult.setParentEntityIdAndCode(
							nativeQueryResult.getParentEntityId() + "##" + nativeQueryResult.getParentEntityCode());
					nativeQueryResult.setIsSelected(true);
				});

		nativeQueryResult.setParentData(parentResult);
		return nativeQueryResult;
	}

	@Transactional
	@Override
	public Boolean saveEnt(String[] upperEntityIdAndType, String entityClass, String objectTypeAndId, Long entityId,
			String entityName) {
		Optional<RoleRightLevelMaster> lblMst = roleRightLevelMasterRepository.findByLevelKeyClass(entityClass);
		if (lblMst.isPresent()) {
			RoleRightLevelMaster lvl = lblMst.get();
			String lvlCode = lvl.getLevelCode();

			if (lvl.getMappingType().equalsIgnoreCase("ONE-TO-ONE")) {
				if (upperEntityIdAndType != null && upperEntityIdAndType.length > 0) {
					String upperEntityIdAndTypeStr = upperEntityIdAndType[0];
					JsonObject jsonObject = makingJsonForHmp(objectTypeAndId, entityId, entityName,
							upperEntityIdAndTypeStr, lvlCode, lvl);
					saveEntitySpecificEntity(CommonHelperFunctions.encodeBase64(jsonObject.toString()));
				}
			} else if (lvl.getMappingType().equalsIgnoreCase("ONE-TO-MANY")) {
				// Delete all the data from HierarchyEntityMap
				hierarchyEntityMapRepository.deleteByObjectIdAndObjectType(entityId, lvlCode);
				if (upperEntityIdAndType != null) {
					for (String upperEntityIdAndTypeStr : upperEntityIdAndType) {
						JsonObject jsonObject = makingJsonForHmp(objectTypeAndId, entityId, entityName,
								upperEntityIdAndTypeStr, lvlCode, lvl);
						saveEntitySpecificEntity(CommonHelperFunctions.encodeBase64(jsonObject.toString()));
					}
				}
			}
		}
		return true;
	}

	private JsonObject makingJsonForHmp(String objectTypeAndId, Long entityId, String entityName,
			String upperEntityIdAndTypeStr, String lvlCode, RoleRightLevelMaster lvl) {
		JsonObject jsonObject = new JsonObject();
		if (upperEntityIdAndTypeStr != null && !upperEntityIdAndTypeStr.isEmpty()) {
			String[] split = upperEntityIdAndTypeStr.split("##");
			String[] split1 = objectTypeAndId.split("##");
			String upperEntityType = split[1];
			String upperEntityName = split[2];
			Long objectId = Long.parseLong(split1[0]);
			String objectType = split1[1];

			List<OrganizationStructureHierarchy> allParents = organizationStructureHierarchyRepository
					.findByLevelCodeAndOrgStrObjIdAndType(upperEntityType, objectId, objectType);
			Optional<OrganizationStructureHierarchy> currentClassEnt = Optional.empty();
			for (OrganizationStructureHierarchy parent : allParents) {
				Optional<OrganizationStructureHierarchy> currentClassEnt1 = organizationStructureHierarchyRepository
						.findByLevelCodeAndParentId(lvlCode, parent.getId());
				if (currentClassEnt1.isPresent()) {
					currentClassEnt = currentClassEnt1;
					break;
				}
			}

			if (currentClassEnt.isPresent()) {

				jsonObject.addProperty("levelCode", lvl.getLevelCode());
				jsonObject.addProperty("orgId", currentClassEnt.get().getId());

				JsonArray selectedEntityIds = new JsonArray();
				JsonObject entity = new JsonObject();
				entity.addProperty("entityId", entityId);
				entity.addProperty("entityName", entityName);

				JsonArray parentEntityIdAndTypeArr = new JsonArray();
				parentEntityIdAndTypeArr.add(upperEntityIdAndTypeStr);
				entity.add("parentEntityId", parentEntityIdAndTypeArr);
				JsonArray parentEntityNameArr = new JsonArray();
				parentEntityNameArr.add(upperEntityName);
				entity.add("parentEntityName", parentEntityNameArr);

				selectedEntityIds.add(entity);
				jsonObject.add("selectedEntityIds", selectedEntityIds);
			}
		}
		return jsonObject;
	}

	@Transactional
	@Override
	public String saveEntitySpecificEntity(String mappingEncodedJsonData) {
		try {
			String decoded = CommonHelperFunctions.decodeBase64(mappingEncodedJsonData);
			JsonObject jsonObject = new Gson().fromJson(decoded, JsonObject.class);
			String levelCode = jsonObject.get("levelCode").getAsString();
			Long orgHigId = jsonObject.get("orgId").getAsLong();
			JsonArray selectedEntityIds = jsonObject.getAsJsonArray("selectedEntityIds");
			for (JsonElement jsonElement : selectedEntityIds) {

				RoleRightLevelMaster roleRightLevelMaster = roleRightLevelMasterRepository.findByLevelCode(levelCode);
				if (roleRightLevelMaster == null) {
					throw new Exception(
							"RoleRightLevelMaster not found for PriviledgeServiceImpl.saveEntitySpecificEntity");
				}
				Optional<OrganizationStructureHierarchy> orgStrHierarchy = organizationStructureHierarchyRepository
						.findById(orgHigId);
				if (!orgStrHierarchy.isPresent()) {
					throw new Exception(
							"OrganizationStructureHierarchy not found for PriviledgeServiceImpl.saveEntitySpecificEntity");
				}

				String tableName = roleRightLevelMaster.getLevelEntityName();
				Class<?> clazz = umtConfigCoreFn.getClassFromTableName(tableName);

				JsonObject entity = jsonElement.getAsJsonObject();
				long entityId = entity.get("entityId").getAsLong();
				String entityName = entity.get("entityName").getAsString();
				JsonArray parentEntityIdAndTypeArr = entity.get("parentEntityId").getAsJsonArray();
				JsonArray parentEntityNameArr = entity.get("parentEntityName").getAsJsonArray();
				// roleRightLevelMaster.getMappingType() ONE-TO-MANY-FIX -> remove -FIX
				String mappingType = roleRightLevelMaster.getMappingType();
				if (mappingType.equalsIgnoreCase("ONE-TO-MANY")) {
					for (JsonElement parentEntityIdAndType : parentEntityIdAndTypeArr) {
						saveEntityHirMap(parentEntityIdAndType.getAsString(), parentEntityNameArr, clazz, tableName,
								entityId, jsonObject, orgStrHierarchy, levelCode, entityName, false);
					}
				} else if (mappingType.equalsIgnoreCase("ONE-TO-ONE")) {
					String parentEntityIdAndType = parentEntityIdAndTypeArr.get(0).getAsString();
					saveEntityHirMap(parentEntityIdAndType, parentEntityNameArr, clazz, tableName, entityId, jsonObject,
							orgStrHierarchy, levelCode, entityName, true);
				}
			}

		} catch (Exception e) {
			log.error("Error in saveEntitySpecificEntity", e);
			return "Error in saveEntitySpecificEntity";
		}
		return "Success";
	}

	@Transactional
	private void saveEntityHirMap(String parentEntityIdAndType, JsonArray parentEntityNameArr, Class<?> clazz,
			String tableName, long entityId, JsonObject jsonObject,
			Optional<OrganizationStructureHierarchy> orgStrHierarchy, String levelCode, String entityName,
			boolean runForParentUpdate) {
		String[] split = parentEntityIdAndType.split("##");
		long parentEntityId = Long.parseLong(split[0]);
		String parentEntityType = split[1];
		String parentEntityName = parentEntityNameArr.get(0).getAsString();
		if (runForParentUpdate) {
			updateEntitySpecificEntity(clazz, tableName, parentEntityType, parentEntityId, entityId, jsonObject);
		}
		HierarchyEntityMap hierarchyEntityMap = new HierarchyEntityMap();
		Optional<HierarchyEntityMap> hierarchy = hierarchyEntityMapRepository
				.findByOrgHirAndEntityIdAndEntityTypeAndPartIdAndType(orgStrHierarchy.get(), entityId, levelCode,
						parentEntityId, parentEntityType);
		if (hierarchy.isPresent()) {
			hierarchyEntityMap = hierarchy.get();
			if (hierarchyEntityMap.getOrganizationStructureHierarchy().getLevelHeight().equals(0)) {
				parentEntityId = hierarchyEntityMap.getParentObjectId();
				parentEntityType = hierarchyEntityMap.getParentObjectType();
				parentEntityName = hierarchyEntityMap.getParentObjectName();
			}
		}
		hierarchyEntityMap.setObjectId(entityId);
		hierarchyEntityMap.setObjectType(levelCode);
		hierarchyEntityMap.setObjectName(entityName);
		hierarchyEntityMap.setParentObjectId(parentEntityId);
		hierarchyEntityMap.setParentObjectType(parentEntityType);
		hierarchyEntityMap.setParentObjectName(parentEntityName);
		hierarchyEntityMap.setOrganizationStructureHierarchy(orgStrHierarchy.get());
		hierarchyEntityMapRepository.save(hierarchyEntityMap);
	}

	/*
	 * @Transactional // Ensures that the transaction is active and commits
	 * automatically public void updateEntitySpecificEntity(Class<?> clazz, String
	 * tableName, String parentEntityType, Long parentEntityId, Long entityId,
	 * JsonObject jsonObject) { log.info("Transaction active: " +
	 * TransactionSynchronizationManager.isActualTransactionActive()); try { String
	 * tableIdName = umtConfigCoreFn.getTableIdName(clazz); String updateQuery =
	 * "UPDATE " + tableName +
	 * " SET parent_object_type = ?, parent_object_id = ? WHERE " + tableIdName +
	 * " = ?"; log.info("updateQuery: " + updateQuery); int update =
	 * jdbcTemplate.update(updateQuery, parentEntityType, parentEntityId, entityId);
	 * if (update == 0) { throw new
	 * Exception("Error in updateEntitySpecificEntity"); }
	 * jsonObject.addProperty("rowAffected", update);
	 * 
	 * } catch (Exception e) { log.error("Error in saveEntitySpecificEntity", e); }
	 * }
	 */

	@Transactional // Ensures that the transaction is active and commits automatically
	public void updateEntitySpecificEntity(Class<?> clazz, String tableName, String parentEntityType,
			Long parentEntityId, Long entityId, JsonObject jsonObject) {
		try {
			String tableIdName = umtConfigCoreFn.getTableIdName(clazz); // Assume this method works as expected
			String updateQuery = "UPDATE " + tableName + " SET parent_object_type = ?, parent_object_id = ? WHERE "
					+ tableIdName + " = ?";
			log.info("Executing Query: " + updateQuery);

			// Create native query with parameters
			Query query = entityManager.createNativeQuery(updateQuery);
			query.setParameter(1, parentEntityType);
			query.setParameter(2, parentEntityId);
			query.setParameter(3, entityId);

			int rowsAffected = query.executeUpdate(); // Execute the update
			jsonObject.addProperty("rowsAffected", rowsAffected);

		} catch (Exception e) {
			log.error("Error in updateEntitySpecificEntity", e);
			jsonObject.addProperty("error", "Update failed: " + e.getMessage());
		}
	}

	// recursive function to get the data from db and create the json object like
	// above
	@SuppressWarnings("unused")
	private JsonArray viewOrgStrHierarchy(Long id, Long parentId) {
		JsonArray jsonArray = new JsonArray();
		List<OrganizationStructureHierarchy> orgStrHierarchyList;
		if (parentId == null) {
			orgStrHierarchyList = organizationStructureHierarchyRepository
					.findByOrganizationStructureIdAndParentNull(id);
		} else {
			orgStrHierarchyList = organizationStructureHierarchyRepository.findByOrganizationStructureId(id, parentId);
		}
		for (OrganizationStructureHierarchy orgStrHierarchy : orgStrHierarchyList) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("value", orgStrHierarchy.getLevelCode());
			jsonObject.add("children", viewOrgStrHierarchy(id, orgStrHierarchy.getId()));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	// recursive function to return get the data from db and create the Map object
	// only value - String and children - Map
	private List<OrganizationStructureParentChild> viewOrgStrHierarchy(Long id, Long parentId,
			List<OrganizationStructureParentChild> orgStrHierarchyList) {
		List<OrganizationStructureHierarchy> orgStrHierarchyList1;
		if (parentId == null) {
			orgStrHierarchyList1 = organizationStructureHierarchyRepository
					.findByOrganizationStructureIdAndParentNull(id);
		} else {
			orgStrHierarchyList1 = organizationStructureHierarchyRepository.findByOrganizationStructureId(id, parentId);
		}
		for (OrganizationStructureHierarchy orgStrHierarchy : orgStrHierarchyList1) {
			OrganizationStructureParentChild orgStrParentChild = new OrganizationStructureParentChild();
			orgStrParentChild.setId(orgStrHierarchy.getId());
			orgStrParentChild.setLevelCode(orgStrHierarchy.getLevelCode());
			orgStrParentChild.setLevelHeight(orgStrHierarchy.getLevelHeight());
			RoleRightLevelMaster lvlMst = roleRightLevelMasterRepository
					.findByLevelCode(orgStrHierarchy.getLevelCode());
			if (lvlMst != null) {
				orgStrParentChild.setMappingType(lvlMst.getMappingType());
			}
			orgStrParentChild.setChildren(viewOrgStrHierarchy(id, orgStrHierarchy.getId(), new ArrayList<>()));
			orgStrHierarchyList.add(orgStrParentChild);
		}
		return orgStrHierarchyList;

	}

}
