package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.RoleMenuPrivilegeMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleMenuPrivilegeMapRepository extends JpaRepository<RoleMenuPrivilegeMap, Long> {

	RoleMenuPrivilegeMap findByRoleMenuIdAndPrivilegeId(Long roleMenuId, Long privilegeId);
}
