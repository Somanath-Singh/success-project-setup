package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long>{

	String roleAccessTypePUBLIC = "and roleAccessType = 'PUBLIC'";

	Role findByRoleCode(String roleCode);
	@Query("FROM Role where isActive=:isActive order by displayName")
	List<Role> findByIsActiveEqual(Boolean isActive);
	


	@Query("FROM Role where  roleId=:id")
	Role findByRoleID(@Param("id") Long id);
	
	@Query("FROM Role where isActive=true and displayOnPage=:display order by displayName")
	List<Role> findByDisplayOnPage(Boolean display);
	
	@Query("FROM Role where isActive=true and displayOnPage=true order by displayName")
	List<Role> findUIRolesOrderByDisplayName();
	
	Role findByRoleLevel(Long roleLevel);

	@Query(value="select * from t_umt_role r\r\n"
			+ "where r.role_level < (select role_level from t_umt_role where role_id = :roleId)\r\n"
			+ "and r.is_active = true and r.is_designation = true and r.display_on_page =true" , nativeQuery=true)
	List<Role> getUpperLevelRoleListByRoleId(Long roleId);

	@Query(value="select * from t_umt_role r\r\n"
			+ "where r.role_level > (select role_level from t_umt_role where role_id = :roleId)\r\n"
			+ "and r.is_active = true and r.is_designation = true  and r.display_on_page =true" , nativeQuery=true)
	List<Role> getLowerLevelRoleListByRoleId(Long roleId);


	List<Role> findByIsActiveTrueAndIsDesignationTrueOrderByDisplayNameAsc();

	Role findByRoleId(Long roleId);


	@Query("select r from Role r where r.displayOnPage = true and r.isActive = true " +
			"and r.roleAccessType = 'PUBLIC' order by r.displayName")
	List<Role> findAllPublicRoles();
	
	List<Role> findAllByIsActiveTrueAndRoleCodeIn(List<String> roleCodes);

	@Query("select r from Role r where r.roleAccessType = :roleAccessType and r.isActive = true")
	List<Role> findByRoleAccessType(@Param("roleAccessType") String roleAccessType);
	
	List<Role> findAllByIsActiveTrueAndIsDesignationTrueOrderByDisplayName();
	
	List<Role> findByIsActiveAndIsDesignationFalseOrderByDisplayNameAsc(boolean b);
	List<Role> findByIsActiveAndIsDesignationTrueOrderByDisplayNameAsc(boolean b);
	
	List<Role> findByIsActiveTrueAndIsRoleApplForMobileTrue();
}
