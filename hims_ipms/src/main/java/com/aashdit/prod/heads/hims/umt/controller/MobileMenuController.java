package com.aashdit.prod.heads.hims.umt.controller;

import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.umt.model.Role;
import com.aashdit.prod.heads.hims.umt.service.MenuService;
import com.aashdit.prod.heads.hims.umt.service.RoleService;

@RequestMapping("/admin/mobile-menu")
@RestController
public class MobileMenuController {

	final static Logger logger = Logger.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/{role}", produces = {
			"application/JSON" }, name = "Get Menus by Role", method = RequestMethod.GET)
	@ResponseBody
	public String getMobileMenuAssignments(@PathVariable("role") String roleCode) {
		ServiceOutcome<Role> svcOutcome = roleService.getRoleByCode(roleCode);
		Role role = svcOutcome.getData();

		JSONArray ja = menuService.getMobileMenuAssignmentsForRole(role);

		return ja.toString();
	}

	@RequestMapping(value = "/assign", produces = {
			"application/JSON" }, name = "Assign Menu to Role", method = RequestMethod.POST)
	@ResponseBody
	public Boolean assignMenu(@RequestParam("data[]") Long[] menuIds, @RequestParam("roleCode") String roleCode) {
		Boolean retVal = false;
		try {
			retVal = menuService.assignMobileMenu(menuIds, roleCode);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return retVal;
	}
}
