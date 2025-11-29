package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.UserRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleMapRepository extends JpaRepository<UserRoleMap, Long> {
	
	UserRoleMap findByUserIdAndRoleId(Long userId, Long roleId);

	Optional<UserRoleMap> findByUserIdAndRoleIdAndIsActiveTrue(Long userId, Long roleId);

	List<UserRoleMap> findByUserId(Long userId);

	List<UserRoleMap> findByUserIdAndIsActiveTrue(Long userId);

	@Query("From UserRoleMap Where roleId=:roleId")
	List<UserRoleMap> findUserRoleMapByRoleId(@Param("roleId") Long roleId);
	
	List<UserRoleMap> findByRoleId(Long roleId);

	@Query(value="select * from public.t_umt_user_role_map rm\r\n"
			+ "where rm.user_id =:userId \r\n"
			+ "and rm.role_id =:roleId \r\n"
			+ "and rm.object_type =:staffPerfType \r\n"
			+ "and rm.object_type_id =:collegeId",nativeQuery =true)
	List<UserRoleMap> findAllRecordsInObjectType(@Param("userId") Long userId, @Param("roleId") Long roleId, @Param("staffPerfType") String staffPerfType, @Param("collegeId") Long collegeId);

	UserRoleMap findByUserIdAndRoleIdAndIsActiveIsTrue(Long userId, Long long1);

	UserRoleMap findByUserIdAndRoleIdAndIsActiveIsTrueAndObjectTypeAndObjectTypeId(Long userId, Long roleId, String objectType, Long objectTypeId);

	UserRoleMap findByUserIdAndRoleIdAndObjectTypeAndObjectTypeId(Long userId, Long roleId, String objectType, Long objectTypeId);
}
