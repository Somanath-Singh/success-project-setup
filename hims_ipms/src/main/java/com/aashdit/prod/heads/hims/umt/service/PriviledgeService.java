package com.aashdit.prod.heads.hims.umt.service;

import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.umt.dto.NativeQueryResults;
import com.aashdit.prod.heads.hims.umt.dto.NativeQueryResultsParent;
import com.aashdit.prod.heads.hims.umt.dto.OrganizationStructureParentChild;
import com.aashdit.prod.heads.hims.umt.model.OrganizationStructureHierarchy;
import com.aashdit.prod.heads.hims.umt.model.Privilege;
import com.aashdit.prod.heads.hims.umt.model.RoleRightLevelMaster;
import com.google.gson.JsonObject;

import java.util.List;

public interface PriviledgeService {

	
	ServiceOutcome<List<Privilege>> findAllPriviledges(Boolean includeInActive);
	
	ServiceOutcome<Privilege> findById(Long privilegeId);
	
	ServiceOutcome<Privilege> savePrivilege(Privilege privilege);
	
	ServiceOutcome<String> assignPrivilege(Long roleId, Long menuId, Long privId);
	
	ServiceOutcome<String> revokePrivilege(Long roleId, Long menuId, Long privId);

    List<RoleRightLevelMaster> getOrganizationLevelList();

	boolean saveOrganizationStructure(String organizationStructureArr);

	List<OrganizationStructureParentChild> viewOrganizationStructure(String filterData) throws Exception;

	List<NativeQueryResults> getEntitySpecificEntity(String entityIdAndTypeEncode, String levelCode, String orgIdEncode, String levelHeight);

	List<NativeQueryResultsParent> getParentResultSet(OrganizationStructureHierarchy orgStrHierarchy);
	String saveEntitySpecificEntity(String mappingEncodedJsonData);

	Boolean saveEnt(String[] upperEntityIdAndType, String entityClass, String objectTypeAndId, Long entityId, String entityName);

	List<NativeQueryResultsParent> getChildResultSet(OrganizationStructureHierarchy hir);

	String getParentObjectIdAndType(long entityId, String entityLevel);

    String getObjectIdAndType(long entityId, String entityLevel);

	JsonObject getHierarchyView(Long entityId, String entityLevel);

//	JsonArray viewOrganizationStructure(String objectIdAndTypeEncode);


}
