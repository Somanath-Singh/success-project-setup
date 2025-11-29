package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.RoleLevelMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleLevelMapRepository extends JpaRepository<RoleLevelMap, Long> {

	@Query("From RoleLevelMap Where roleId=:roleId and levelId=:levelId")
	RoleLevelMap findByRoleIdAndLevelId(@Param("roleId") Long roleId, @Param("levelId") Long levelId);

	List<RoleLevelMap> findByRoleId(Long roleId);

	@Query("select r from RoleLevelMap r where r.roleId = ?1 and r.roleLevelId = ?2 and r.isActive = true")
	Optional<RoleLevelMap> findByRoleIdAndRoleLevelId(Long roleId, Long levelId);
}
