package com.aashdit.setup.umt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.setup.umt.model.HierarchyEntityMap;
import com.aashdit.setup.umt.model.OrganizationStructureHierarchy;

public interface HierarchyEntityMapRepository extends JpaRepository<HierarchyEntityMap, Long> {
    @Query("select h from HierarchyEntityMap h " +
            "where h.organizationStructureHierarchy = :organizationStructureHierarchy and h.isActive = true")
    List<HierarchyEntityMap> findByOrganizationStructureHierarchy(@Param("organizationStructureHierarchy") OrganizationStructureHierarchy organizationStructureHierarchy);

    @Query("select h from HierarchyEntityMap h " +
            "where h.organizationStructureHierarchy = :organizationStructureHierarchy and h.objectId = :objectId and h.objectType = :objectType ")
    Optional<HierarchyEntityMap> findByOrgHirAndEntityIdAndEntityType(@Param("organizationStructureHierarchy") OrganizationStructureHierarchy organizationStructureHierarchy, @Param("objectId") Long objectId, @Param("objectType") String objectType);

    @Query("select h from HierarchyEntityMap h " +
            "where h.objectId = :objectId and h.objectType = :objectType ")
    Optional<HierarchyEntityMap> findByEntityIdAndEntityType(@Param("objectId") Long objectId, @Param("objectType") String objectType);


    @Query("select h from HierarchyEntityMap h " +
            "where h.organizationStructureHierarchy = :organizationStructureHierarchy and h.objectId = :objectId and h.objectType = :objectType  and h.parentObjectId = :parentObjectId and h.parentObjectType = :parentObjectType")
    Optional<HierarchyEntityMap> findByOrgHirAndEntityIdAndEntityTypeAndPartIdAndType(@Param("organizationStructureHierarchy") OrganizationStructureHierarchy organizationStructureHierarchy,
                                                                                      @Param("objectId") Long objectId,
                                                                                      @Param("objectType") String objectType,
                                                                                      @Param("parentObjectId") Long parentObjectId,
                                                                                      @Param("parentObjectType") String parentObjectType);


    @Query("select h from HierarchyEntityMap h " +
            "where h.objectId = :objectId and h.objectType = :objectType ")
    Optional<HierarchyEntityMap> findByOrgHirAndEntityIdAndEntityType(@Param("objectId") Long objectId, @Param("objectType") String objectType);


    List<HierarchyEntityMap> findAllByOrganizationStructureHierarchyOrganizationStructureObjectIdAndObjectTypeAndIsActiveTrue(Long organizationStructureHierarchyId, String entityType);

    @Query("select h from HierarchyEntityMap h " +
            "where h.parentObjectId = :parentObjectId and h.parentObjectType = :parentObjectType and h.isActive = true")
    List<HierarchyEntityMap> findByParentObjectIdAndParentObjectType(@Param("parentObjectId") Long parentObjectId, @Param("parentObjectType") String parentObjectType);

	List<HierarchyEntityMap> findAllByOrganizationStructureHierarchyOrganizationStructureObjectIdAndOrganizationStructureHierarchyOrganizationStructureObjectTypeAndObjectTypeAndIsActiveTrue(
            Long valueOf, String string, String string2);

    @Modifying
    @Query("delete from HierarchyEntityMap h where h.organizationStructureHierarchy = :organizationStructureHierarchy")
    void deleteByOrgHir(OrganizationStructureHierarchy organizationStructureHierarchy);

    @Modifying
    @Query("delete from HierarchyEntityMap h where h.objectId = :objectId and h.objectType = :objectType")
    void deleteByObjectIdAndObjectType(Long objectId, String objectType);

    @Query("select h from HierarchyEntityMap h " +
            "where h.organizationStructureHierarchy = :organizationStructureHierarchy and h.objectType = :objectType and h.objectId not in :objectIds")
    List<HierarchyEntityMap> findByOrganizationStructureHierarchyEqualsAndObjectTypeEqualsAndObjectIdNotIn(@Param("organizationStructureHierarchy") OrganizationStructureHierarchy organizationStructureHierarchy, @Param("objectType") String objectType, @Param("objectIds") List<Long> objectIds);
}