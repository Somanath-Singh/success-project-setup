package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.RoleMenuMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuMapRepository extends JpaRepository<RoleMenuMap, Long>{


	RoleMenuMap findByRoleIdAndMenuId(Long roleid, Long menuId);
	
	List<RoleMenuMap> findByRoleId(Long rleId);

	List<RoleMenuMap> findByRoleIdAndIsActiveForMobileTrue(Long roleId);
}
