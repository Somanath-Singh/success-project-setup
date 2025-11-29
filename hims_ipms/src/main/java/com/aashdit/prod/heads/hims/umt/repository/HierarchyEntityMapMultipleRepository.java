package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.HierarchyEntityMap;
import com.aashdit.prod.heads.hims.umt.model.HierarchyEntityMapMultiple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HierarchyEntityMapMultipleRepository extends JpaRepository<HierarchyEntityMapMultiple, Long> {

    @Query("select h from HierarchyEntityMapMultiple h " +
            "where h.hierarchyEntityMap = :hierarchyEntityMap and h.parentObjectId = :parentObjectId and h.parentObjectType = :parentObjectType")
    Optional<HierarchyEntityMapMultiple> findByHierarchyEntityMapAndParentObjectIdAndParentObjectType(@Param("hierarchyEntityMap") HierarchyEntityMap hierarchyEntityMap, @Param("parentObjectId") Long parentObjectId, @Param("parentObjectType") String parentObjectType);

    @Query("select h from HierarchyEntityMapMultiple h where h.hierarchyEntityMap = :hierarchyEntityMap")
    List<HierarchyEntityMapMultiple> findByHierarchyEntityMap(@Param("hierarchyEntityMap") HierarchyEntityMap hierarchyEntityMap);
}