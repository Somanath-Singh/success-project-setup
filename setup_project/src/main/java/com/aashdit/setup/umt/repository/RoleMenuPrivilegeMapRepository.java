package com.aashdit.setup.umt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.setup.umt.model.RoleMenuPrivilegeMap;

public interface RoleMenuPrivilegeMapRepository extends JpaRepository<RoleMenuPrivilegeMap, Long> {

	RoleMenuPrivilegeMap findByRoleMenuIdAndPrivilegeId(Long roleMenuId, Long privilegeId);
}
