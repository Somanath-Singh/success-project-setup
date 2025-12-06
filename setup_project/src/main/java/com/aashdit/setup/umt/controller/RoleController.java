package com.aashdit.setup.umt.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.model.Role;
import com.aashdit.setup.umt.model.RoleLevelMap;
import com.aashdit.setup.umt.service.RoleService;
import com.aashdit.setup.umt.specs.UmtConfigCoreFn;
import com.aashdit.setup.umt.utils.CommonHelperFunctions;

@Controller
@RequestMapping(value = "/admin/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UmtConfigCoreFn umtConfigCoreFn;

	@GetMapping(path = "/list", name = "Role List")
	public String userList(Model model, @RequestParam(required = false) String filterData) {
		List<Role> roleList = new ArrayList<>();
		if (filterData != null && !filterData.isEmpty()) {
			Base64.Decoder decoder = Base64.getDecoder();
			filterData = new String(decoder.decode(filterData));
			String[] filterDataArray = filterData.split("##");
			model.addAttribute("selectedFilterEntityObjectIdAndValue", filterData);
			roleList = roleService.getEntityOnlySpecificRoleList(Long.parseLong(filterDataArray[0]),
					filterDataArray[1]);
		} else {
			ServiceOutcome<List<Role>> outcome = roleService.getAllRoles(true);
			if (outcome.getOutcome()) {
				roleList = outcome.getData();
			}
		}
		model.addAttribute("roleList", roleList);

		umtConfigCoreFn.addEntityIdAndLevelToModelWithData(model, Role.class, false, "", false, false);

		return "site.admin.roleList";

	}

	@GetMapping(path = "/edit/{id}", name = "Edit Role")
	public String userEdit(Model model, @PathVariable("id") Long roleId, HttpServletRequest request) {

		ServiceOutcome<Role> outcome = roleService.findByRoleId(roleId);
		if (outcome.getOutcome()) {
			model.addAttribute("serviceOutcome", outcome);
		} else {
			model.addAttribute("error_msg", outcome.getMessage());
		}
		umtConfigCoreFn.addEntityIdAndLevelToModelWithData(model, Role.class, false,
				outcome.getData().getObjectIdAndType(), true, false);
		return "site.admin.roleEdit";

	}

	@GetMapping(path = "/view/{id}", name = "Role View")
	public String userView(Model model, @PathVariable("id") Long roleId) {

		ServiceOutcome<Role> outcome = roleService.findByRoleId(roleId);
		if (outcome.getOutcome()) {
			model.addAttribute("serviceOutcome", outcome);
		} else {
			model.addAttribute("error_msg", outcome.getMessage());
		}

		return "site.admin.roleView";

	}

	@PostMapping(path = "/isActive", name = "Active And InActive Role")
	public String lockNUnlock(RedirectAttributes attr, Boolean isActive, Long roleId) {

		ServiceOutcome<Role> serviceOutcome = roleService.lockNUnlockRoleById(roleId, isActive);
		if (serviceOutcome.getOutcome()) {
			attr.addFlashAttribute("success_msg", serviceOutcome.getMessage());
		} else {
			attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
		}

		return "redirect:/admin/role/list";

	}

	@PostMapping(path = "/addNupdate", name = "Add And Update Role")
	public String userUpdate(RedirectAttributes attr, Role role) {

		ServiceOutcome<Role> serviceOutcome = roleService.addNupdateRole(role);
		if (serviceOutcome.getOutcome()) {
			attr.addFlashAttribute("success_msg", serviceOutcome.getMessage());
		} else {
			attr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
		}

		return "redirect:/admin/role/list";
	}

	@GetMapping(path = "/roleLevelMap/{id}", name = "Role Leve Map")
	public String roleLevelMap(@PathVariable("id") Long roleId, Model model) {

		ServiceOutcome<List<RoleLevelMap>> serviceOutcome = roleService.findRoleLevelListByRoleId(roleId);
		if (!serviceOutcome.getOutcome()) {
			model.addAttribute("error_msg", serviceOutcome.getMessage());
		} else {
			model.addAttribute("roleLevelList", roleService.roleRightLevelList().getData());
			model.addAttribute("roleData", roleService.findByRoleId(roleId).getData());
			model.addAttribute("roleMapList", serviceOutcome.getData());
		}

		return "site.admin.roleLevelMapList";

	}

	@PostMapping(path = "/roleLevelMap/addNupdate", name = "Role to Level Assignment", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ServiceOutcome<RoleLevelMap> addNupdate(RedirectAttributes attr,
			@RequestParam("maxAllocations") Long[] maxAllocations, @RequestParam("roleLevelId") Long[] roleLevelId,
			@RequestParam("status") Boolean[] status, Long roleId) {

		ServiceOutcome<RoleLevelMap> serviceOutcome = roleService.addNupdateRoleLevelMap(maxAllocations, roleLevelId,
				status, roleId);

		/*
		 * if(serviceOutcome.getOutcome()) { attr.addFlashAttribute("success_msg",
		 * serviceOutcome.getMessage()); }else { attr.addFlashAttribute("error_msg",
		 * serviceOutcome.getMessage());
		 * 
		 * }
		 */

		return serviceOutcome; // "redirect:/admin/role/list";

	}

	@GetMapping(path = "/validate-role-code", name = "Validate Role Code")
	public @ResponseBody String validateUser(String roleCode) {
		JSONObject jsonObject = new JSONObject();
		ServiceOutcome<Role> roleOutcome = roleService.getRoleByCode(roleCode);
		if (roleOutcome.getData() != null) {
			jsonObject.put("isDuplicate", true);
		} else {
			jsonObject.put("isDuplicate", false);
		}
		return jsonObject.toString();

	}

	@GetMapping(path = "/list-by-entityIdAndUserLevel", name = "Role List By EntityId And UserLevel")
	@ResponseBody
	public ResponseEntity<?> roleList(@RequestParam("entityIdAndUserLevel") String entityIdAndUserLevel) {
		try {
			return new ResponseEntity<>(CommonHelperFunctions.onGsonArraySuccess(
					roleService.getAllRolesByEntityIdAndUserLevel(entityIdAndUserLevel)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
