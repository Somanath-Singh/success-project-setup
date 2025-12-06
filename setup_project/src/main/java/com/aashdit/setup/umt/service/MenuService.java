package com.aashdit.setup.umt.service;

import java.util.List;

import org.json.JSONArray;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.model.Menu;
import com.aashdit.setup.umt.model.Privilege;
import com.aashdit.setup.umt.model.Role;


public interface MenuService {

	JSONArray getAllMenus(Boolean isActive, Role role);
	
	Boolean  assignMenu(Long[] menuIds, String role);
	
	List<Menu> getUIMenus(Role role);
	
	List<Privilege> getPriviledgesForRoleAndMenu(Long roleId, Long menuId);
	
	List<Menu> getAllMenus();


	ServiceOutcome<Menu> saveModule(Menu menu);
	
	ServiceOutcome<List<Menu>> getModuleList();
	
	ServiceOutcome<Menu> getModule(Long menuId);
	
	ServiceOutcome<List<Menu>> getAppMenuList(Boolean init);
	
	ServiceOutcome<Menu> saveMenu(Menu menu);
	
	JSONArray getMenusForReorder();
	
	Boolean saveMenuOrder(Long menuId, Long parentMenuId, Integer order);
	
	JSONArray getAllAssingedMenusForRole(Role role);
	
	/* API Sepecific Calls */
	ServiceOutcome<List<Menu>> getMenuPagedList();
	
	ServiceOutcome<Menu> getMenuById(Long menuId);

	List<Menu> findUIMenusByRole(Long roleId);

	JSONArray getMobileMenuAssignmentsForRole(Role role);

	Boolean  assignMobileMenu(Long[] menuIds, String role);
}
