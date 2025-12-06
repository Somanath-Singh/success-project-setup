package com.aashdit.setup.umt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.umt.model.UserRoleRightLevel;

import java.util.List;

@Repository
public interface UserRoleRightLevelRepository extends JpaRepository<UserRoleRightLevel, Long> {

	@Query("FROM UserRoleRightLevel where userRoleId = :userRoleId AND roleLevelId = :roleLevelId AND objectId = :objectId")
	UserRoleRightLevel find(@Param("userRoleId") Long userRoleId, @Param("roleLevelId") Long roleLevelId, @Param("objectId") Long objectId);

	@Query("FROM UserRoleRightLevel where userRoleId = :userRoleId")
	UserRoleRightLevel findByUserRoleId(@Param("userRoleId") Long userRoleId);

	void deleteByUserRoleId(Long userRoleMapId);

	void deleteByRoleLevelId(Long roleLevelMapId);
	
	@Query("FROM UserRoleRightLevel where userRoleId = :userRoleId AND roleLevelId = :roleLevelId")
	List<UserRoleRightLevel> getAllocatedValues(@Param("userRoleId") Long userRoleId, @Param("roleLevelId") Long roleLevelId);

	// userRoleId
	@Query("FROM UserRoleRightLevel where userRoleId = :userRoleId AND isActive = true")
	List<UserRoleRightLevel> findByUserRoleMapId(@Param("userRoleId") Long userRoleId);

}
