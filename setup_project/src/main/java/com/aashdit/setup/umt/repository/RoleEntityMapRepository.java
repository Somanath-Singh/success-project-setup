package com.aashdit.setup.umt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.setup.umt.model.RoleEntityMap;

import java.util.List;
import java.util.Optional;

public interface RoleEntityMapRepository extends JpaRepository<RoleEntityMap, Long> {
    @Query("select r from RoleEntityMap r where r.entityId = ?1 and r.entityLevel = ?2")
    List<RoleEntityMap> findByEntityIdAndLevel(Long entityId, String entityLevel);

    @Query("select r from RoleEntityMap r where r.entityId = ?1 and r.entityLevel = ?2 and r.roleCode = ?3 and r.isActive = true")
    Optional<RoleEntityMap> findByEntityIdAndLevelRoleCode(Long entityId, String entityLevel, String roleCode);

    @Query("select r from RoleEntityMap r where r.roleCode = :roleCode and r.isActive = true")
    List<RoleEntityMap> findByRoleCodeEqual(@Param("roleCode") String roleCode);

	List<RoleEntityMap> findAllByEntityIdAndEntityLevelAndIsActiveTrue(Long entityId, String entityLevel);

	List<RoleEntityMap> findByEntityIdAndEntityLevel(long entityIdLong, String entityLevel);
}