package com.aashdit.setup.umt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.umt.model.RoleMenuMap;

import java.util.List;

@Repository
public interface RoleMenuMapRepository extends JpaRepository<RoleMenuMap, Long>{


	RoleMenuMap findByRoleIdAndMenuId(Long roleid, Long menuId);
	
	List<RoleMenuMap> findByRoleId(Long rleId);

	List<RoleMenuMap> findByRoleIdAndIsActiveForMobileTrue(Long roleId);
}
