package com.aashdit.setup.umt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.setup.umt.model.OrganizationStructure;

import java.util.List;
import java.util.Optional;

public interface OrganizationStructureRepository extends JpaRepository<OrganizationStructure, Long> {

    @Query("select o from OrganizationStructure o where o.objectId = :objectId and o.objectType = :objectType")
    List<OrganizationStructure> findByObjectIdAndObjectTypeAll(@Param("objectId") Long objectId, @Param("objectType") String objectType);

    @Query("select o from OrganizationStructure o " +
            "where o.objectId = :objectId and o.objectType = :objectType and o.isActive = true")
    Optional<OrganizationStructure> findByObjectIdAndObjectType(@Param("objectId") Long objectId, @Param("objectType") String objectType);
}