package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.OrganizationStructureHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrganizationStructureHierarchyRepository extends JpaRepository<OrganizationStructureHierarchy, Long> {

    @Query("select o from OrganizationStructureHierarchy o where o.isActive = true and o.organizationStructure.id = :id and o.parentHierarchyId = :parentId")
    List<OrganizationStructureHierarchy> findByOrganizationStructureId(@Param("id") Long id, @Param("parentId") Long parentId);

    @Query("select o from OrganizationStructureHierarchy o where o.isActive = true and o.parentHierarchyId = :parentId")
    List<OrganizationStructureHierarchy> findByParentHieIdId(@Param("parentId") Long parentId);

    @Query("select o from OrganizationStructureHierarchy o where o.isActive = true and o.organizationStructure.id = :id and o.parentHierarchyId is null")
    List<OrganizationStructureHierarchy> findByOrganizationStructureIdAndParentNull(@Param("id") Long id);

    @Query("select o from OrganizationStructureHierarchy o " +
            "where o.levelCode = :levelCode and o.organizationStructure.objectId = :objectId and o.organizationStructure.objectType = :objectType and o.isActive = true")
    List<OrganizationStructureHierarchy> findByLevelCodeAndOrgStrObjIdAndType(@Param("levelCode") String levelCode, @Param("objectId") Long objectId, @Param("objectType") String objectType);

    @Query("select o from OrganizationStructureHierarchy o where o.isActive = true and o.organizationStructure.objectId = :id and o.organizationStructure.objectType = :type")
	List<OrganizationStructureHierarchy> findAllUniqueByOrganizationStructureId(@Param("id") Long id, @Param("type") String type);

    @Query("select o from OrganizationStructureHierarchy o " +
            "where o.levelCode = :levelCode and o.parentHierarchyId = :parentHierarchyId and o.isActive = true")
    Optional<OrganizationStructureHierarchy> findByLevelCodeAndParentId(@Param("levelCode") String levelCode, @Param("parentHierarchyId") Long parentHierarchyId);
}