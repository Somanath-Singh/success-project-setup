package com.aashdit.prod.heads.hims.umt.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.umt.dto.OrganizationStructureParentChild;
import com.aashdit.prod.heads.hims.umt.dto.RoleEntityCommonDto;
import com.aashdit.prod.heads.hims.umt.model.Menu;
import com.aashdit.prod.heads.hims.umt.model.OrganizationStructure;
import com.aashdit.prod.heads.hims.umt.model.Privilege;
import com.aashdit.prod.heads.hims.umt.model.Role;
import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.service.MenuService;
import com.aashdit.prod.heads.hims.umt.service.PriviledgeService;
import com.aashdit.prod.heads.hims.umt.service.RoleService;
import com.aashdit.prod.heads.hims.umt.specs.UmtConfigCoreFn;
import com.aashdit.prod.heads.hims.umt.utils.CommonHelperFunctions;
import com.aashdit.prod.heads.hims.umt.utils.SecurityHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
@RequestMapping(path = "/system")
public class SystemController {

	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private PriviledgeService priviledgeService;

	@Autowired
	private UmtConfigCoreFn umtConfigCoreFn;

	@GetMapping(path = "/index", name = "System Dashboard")
	private String index(Model model) {
		// log.debug(LocaleContextHolder.getLocale().getLanguage());
		/*
		 * ServiceOutcome<List<State>> svcOutcome = accessService.getByRoleLevel(0L, 1L,
		 * AccessLevelType.STATE, State.class); List<State> lstState =
		 * svcOutcome.getData(); lstState = new
		 * LocaleSpecificSorter<State>(State.class).sort(lstState);
		 */

		model.addAttribute("lstState", new ArrayList<>());

		return "sysAdmin.home";
	}

	@GetMapping(path = "/setup/module", name = "List Module")
	private String setupModule(Model model) {
		ServiceOutcome<List<Menu>> serviceOutcome = new ServiceOutcome<>();
		try {
			serviceOutcome = menuService.getModuleList();
			model.addAttribute("lstModule", serviceOutcome.getData());
		} catch (Exception ex) {
			log.error(ex);

			model.addAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return "sysAdmin.setup.module";
	}

	@GetMapping(path = "/setup/module/add", name = "Add Module")
	private String addModule(Model model) {
		Menu menu = new Menu();
		model.addAttribute("menu", menu);

		return "sysAdmin.setup.module.add";
	}

	@GetMapping(path = "/setup/module/edit/{id}", name = "Edit Module")
	private String editModule(@PathVariable Long id, Model model) {
		ServiceOutcome<Menu> svcOutcome = new ServiceOutcome<>();
		try {
			svcOutcome = menuService.getModule(id);
			if (svcOutcome.getOutcome() == true) {
				model.addAttribute("menu", svcOutcome.getData());
			} else {
				model.addAttribute("error_msg",
						messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}
		} catch (Exception ex) {
			log.error(ex);
			model.addAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return "sysAdmin.setup.module.edit";
	}

	@PostMapping(path = "/setup/module/save", name = "Save Module")
	private String saveModule(RedirectAttributes redirectAttr, Menu menu) {
		ServiceOutcome<Menu> serviceOutcome = new ServiceOutcome<>();
		try {
			serviceOutcome = menuService.saveModule(menu);
			if (serviceOutcome.getOutcome() == true) {
				redirectAttr.addFlashAttribute("success_msg",
						messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			} else {
				redirectAttr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex);

			redirectAttr.addFlashAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return "redirect:/system/setup/module";
	}

	/* Menu Population from System */
	@GetMapping(path = "/setup/menu/init", name = "Populate Menu")
	private String setupMenuInit(Model model) {
		ServiceOutcome<List<Menu>> serviceOutcome = new ServiceOutcome<>();
		try {
			serviceOutcome = menuService.getAppMenuList(true);
			List<Menu> lstMenus = serviceOutcome.getData();
			User user = SecurityHelper.getCurrentUser();
			if (user.getPrimaryRole() != null && !user.getPrimaryRole().getRoleCode().equals("ROLE_SYSTEM")) {
				lstMenus = lstMenus.stream().filter(m -> !m.getMenuURL().startsWith("/system"))
						.collect(Collectors.toList());
			}

			model.addAttribute("lstMenu", lstMenus);
		} catch (Exception ex) {
			log.error(ex);

			model.addAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return "sysAdmin.setup.menu";
	}

	@GetMapping(path = "/setup/menu/edit/{id}", name = "Edit Menu")
	private String editMenu(@PathVariable Long id, Model model) {
		ServiceOutcome<Menu> svcOutcome = new ServiceOutcome<>();
		try {
			svcOutcome = menuService.getModule(id);
			if (svcOutcome.getOutcome() == true) {
				model.addAttribute("menu", svcOutcome.getData());
			} else {
				model.addAttribute("error_msg",
						messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
			}
		} catch (Exception ex) {
			log.error(ex);
			model.addAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return "sysAdmin.setup.menu.edit";
	}

	@PostMapping(path = "/setup/menu/save", name = "Save Menu")
	private String saveMenu(RedirectAttributes redirectAttr, Menu menu) {
		ServiceOutcome<Menu> serviceOutcome = new ServiceOutcome<>();
		try {
			serviceOutcome = menuService.saveMenu(menu);
			if (serviceOutcome.getOutcome() == true) {
				redirectAttr.addFlashAttribute("success_msg",
						messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			} else {
				redirectAttr.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			log.error(ex);

			redirectAttr.addFlashAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return "redirect:/system/setup/menu/list";
	}

	@GetMapping(path = "/setup/menu/list", name = "List Menus")
	private String listMenu(Model model) {
		ServiceOutcome<List<Menu>> serviceOutcome = new ServiceOutcome<>();
		try {
			serviceOutcome = menuService.getAppMenuList(false);
			List<Menu> lstMenus = serviceOutcome.getData();
			User user = SecurityHelper.getCurrentUser();
			if (user.getPrimaryRole() != null && !user.getPrimaryRole().getRoleCode().equals("ROLE_SYSTEM")) {
				lstMenus = lstMenus.stream().filter(m -> !m.getMenuURL().startsWith("/system"))
						.collect(Collectors.toList());
			}

			model.addAttribute("lstMenu", lstMenus);
		} catch (Exception ex) {
			log.error(ex);

			model.addAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return "sysAdmin.setup.menu";
	}

	@GetMapping(path = "/setup/menu/add", name = "Add Sub Menu")
	private String addMenu(Model model) {
		Menu menu = new Menu();
		model.addAttribute("menu", menu);

		ServiceOutcome<List<Menu>> serviceOutcome = menuService.getModuleList();
		model.addAttribute("lstModule", serviceOutcome.getData());

		return "sysAdmin.setup.menu.add";
	}

	@GetMapping(path = "/setup/menu/map", name = "Super User Role Menu Mapping")
	private String mapRoleMenu(Model model) {
		ServiceOutcome<List<Role>> svcOutcome = new ServiceOutcome<>();
		svcOutcome = roleService.getAllRoles(false);

		if (svcOutcome.getOutcome() == true) {
			List<Role> roleList = svcOutcome.getData();
			model.addAttribute("roleList", roleList);
		} else {
			model.addAttribute("error_msg", svcOutcome.getMessage());
		}

		return "admin.menu.role.map";
	}

	@GetMapping(path = "/setup/privilege/list", name = "List Privilege")
	private String listPrivilege(Model model) {

		try {
			ServiceOutcome<List<Privilege>> svcOutcome = priviledgeService.findAllPriviledges(true);
			if (svcOutcome.getOutcome()) {
				List<Privilege> lstPrivileges = svcOutcome.getData();
				model.addAttribute("lstPrivileges", lstPrivileges);
			} else {
				model.addAttribute("error_msg", svcOutcome.getMessage());
				model.addAttribute("lstPrivileges", null);
			}

		} catch (Exception ex) {
			log.error(ex);

			model.addAttribute("error_msg",
					messageSource.getMessage("app.system.error", null, LocaleContextHolder.getLocale()));
			model.addAttribute("lstPrivileges", null);
		}

		return "system.privilege.list";
	}

	@GetMapping(path = "/setup/privilege/edit/{id}", name = "Edit Priviledge")
	private String editPrivilege(@PathVariable Long id, Model model) {
		ServiceOutcome<Privilege> svcOutcome = new ServiceOutcome<Privilege>();
		try {
			svcOutcome = priviledgeService.findById(id);
			if (svcOutcome.getOutcome()) {
				Privilege privilege = svcOutcome.getData();
				model.addAttribute("privilege", privilege);
			} else {
				model.addAttribute("error_msg", svcOutcome.getMessage());
				model.addAttribute("lstPrivileges", null);
			}

		} catch (Exception ex) {
			log.error(ex);

			model.addAttribute("error_msg",
					messageSource.getMessage("app.system.error", null, LocaleContextHolder.getLocale()));
			model.addAttribute("privilege", null);
		}

		return "system.privilege.edit";
	}

	@PostMapping(path = "/setup/privilege/save", name = "Save Priviledge")
	private String savePrivilege(@ModelAttribute Privilege privilege, RedirectAttributes attr) {
		ServiceOutcome<Privilege> svcOutcome = new ServiceOutcome<Privilege>();
		try {
			svcOutcome = priviledgeService.savePrivilege(privilege);
			if (svcOutcome.getOutcome()) {
				attr.addFlashAttribute("success_msg",
						messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			} else {
				attr.addFlashAttribute("error_msg", svcOutcome.getMessage());
			}

		} catch (Exception ex) {
			log.error(ex);

			attr.addFlashAttribute("error_msg",
					messageSource.getMessage("app.system.error", null, LocaleContextHolder.getLocale()));
		}

		return "redirect:/system/setup/privilege/list";
	}

	@GetMapping(path = "/setup/privilege/add", name = "Add Privilege")
	public String addPrivilege(Model model) {
		Privilege privilege = new Privilege();
		model.addAttribute("privilege", privilege);

		return "system.privilege.add";
	}

	@GetMapping("/create-structure")
	public String createStructure(Model model, @RequestParam(required = false) String filterData,
			@RequestParam(required = false) String textOrgName) {
		try {
			String selectedEntityId = "";
			if (filterData != null && !filterData.isEmpty()) {
				Base64.Decoder decoder = Base64.getDecoder();
				String decode = new String(decoder.decode(filterData));
				String[] split = decode.split("##");
				Long entityId = Long.parseLong(split[0]);
				String entityLevel = split[1];
				selectedEntityId = entityId + "##" + entityLevel;
				String orgName = new String(decoder.decode(textOrgName));
				model.addAttribute("orgName", orgName);
				model.addAttribute("entityLevel", entityLevel);
				model.addAttribute("orgNameId", entityId);

				List<OrganizationStructureParentChild> hierarchyList = priviledgeService
						.viewOrganizationStructure(filterData);
				model.addAttribute("hierarchyList", hierarchyList);

			}
			model.addAttribute("organizationLevelList", priviledgeService.getOrganizationLevelList());
			umtConfigCoreFn.addEntityIdAndLevelToModelWithData(model, OrganizationStructure.class, true,
					selectedEntityId, false, true);
		} catch (Exception e) {
			log.error("Error in createStructure", e);
		}
		return "site.createStructure";
	}

	@SuppressWarnings("unused")
	@GetMapping("/create-structure-new")
	public String createStructureNew(Model model, @RequestParam(required = false) String filterData,
			@RequestParam(required = false) String textOrgName) {
		try {
			String selectedEntityId = "";
			if (filterData != null && !filterData.isEmpty()) {

				Base64.Decoder decoder = Base64.getDecoder();
				String decode = new String(decoder.decode(filterData));
				String[] split = decode.split("##");
				Long entityId = Long.parseLong(split[0]);
				String entityLevel = split[1];
				selectedEntityId = entityId + "##" + entityLevel;
				String orgName = new String(decoder.decode(textOrgName));
			}
		} catch (Exception e) {
			log.error("Error in createStructure", e);
		}
		return "site.createNewStructure";
	}

	@GetMapping("/getLevels")
	@ResponseBody
	public ResponseEntity<?> getLevels() {
		try {
			return new ResponseEntity<>(CommonHelperFunctions.onSuccess(priviledgeService.getOrganizationLevelList()),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save-structure")
	public String saveStructure(@RequestParam("organizationStructureArr") String organizationStructureArr,
			RedirectAttributes attr) {
		try {
			boolean isSaved = priviledgeService.saveOrganizationStructure(organizationStructureArr);
			if (isSaved) {
				attr.addFlashAttribute("success_msg", "Organization structure saved successfully");
			}
		} catch (Exception e) {
			log.error("Error in saveStructure", e);
		}
		return "redirect:/system/create-structure";
	}

	@GetMapping("/getEntitySpecificEntity")
	@ResponseBody
	public ResponseEntity<?> getEntitySpecificEntity(
			@RequestParam("entityIdAndTypeEncode") String entityIdAndTypeEncode,
			@RequestParam("levelCode") String levelCode, @RequestParam("orgId") String orgIdEncode,
			@RequestParam("levelHeight") String levelHeight) {
		try {
			return new ResponseEntity<>(CommonHelperFunctions.onSuccess(priviledgeService
					.getEntitySpecificEntity(entityIdAndTypeEncode, levelCode, orgIdEncode, levelHeight)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/saveEntitySpecificEntity")
	@ResponseBody
	public ResponseEntity<?> saveEntitySpecificEntity(@RequestParam("mappingData") String mappingEncodedJsonData) {
		try {
			return new ResponseEntity<>(
					CommonHelperFunctions.onSuccess(priviledgeService.saveEntitySpecificEntity(mappingEncodedJsonData)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getParentEntityHierarchyList")
	@ResponseBody // entityId, classObjectValue
	public ResponseEntity<?> getEntitiesByCurrentUser(@RequestParam("entityId") String entityId,
			@RequestParam("classObjectValue") String classObjectValue,
			@RequestParam("currentClassObjectId") String currentClassObjectId) {
		try {
			// decode
			Base64.Decoder decoder = Base64.getDecoder();
			String entityIdDecode = new String(decoder.decode(entityId));
			String[] split = entityIdDecode.split("##");
			long entityIdLong = Long.parseLong(split[0]);
			String entityLevel = split[1];
			String classObjectValueDecode = new String(decoder.decode(classObjectValue));
			Long currentObjectId = currentClassObjectId != null && !currentClassObjectId.isEmpty()
					? Long.parseLong(CommonHelperFunctions.decodeBase64(currentClassObjectId))
					: 0L;
			return new ResponseEntity<>(CommonHelperFunctions.onSuccess(umtConfigCoreFn.getParentEntities(entityIdLong,
					entityLevel, classObjectValueDecode, currentObjectId)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getChildListEntityHierarchyList")
	@ResponseBody
	public ResponseEntity<?> getChildListEntityHierarchyList(@RequestParam("entityId") String entityId,
			@RequestParam(value = "classObjectValue", required = false) String classObjectValue,
			@RequestParam(value = "currentClassObjectId", required = false) String currentClassObjectId) {
		try {
			// decode
			Base64.Decoder decoder = Base64.getDecoder();
			String entityIdDecode = new String(decoder.decode(entityId));
			String[] split = entityIdDecode.split("##");
			long entityIdLong = Long.parseLong(split[0]);
			String entityLevel = split[1];
			long currentObjectId = 0L;
			if (currentClassObjectId != null) {
				String s = CommonHelperFunctions.decodeBase64(currentClassObjectId);
				currentObjectId = s.split("##").length > 1 ? Long.parseLong(s.split("##")[0]) : 0L;
			}
			return new ResponseEntity<>(CommonHelperFunctions.onSuccess(
					umtConfigCoreFn.getChildListEntityHierarchyList(entityIdLong, entityLevel, currentObjectId)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getCurrentEntityChildListEntityHierarchyList")
	@ResponseBody
	public ResponseEntity<?> getCurrentEntityChildListEntityHierarchyList() {
		try {
			Long entityIdLong = SecurityHelper.getCurrentUserEntityId();
			String entityLevel = SecurityHelper.getCurrentUserEntityLevel();
			return new ResponseEntity<>(
					CommonHelperFunctions
							.onSuccess(umtConfigCoreFn.getChildListEntityHierarchyList(entityIdLong, entityLevel, 0L)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getPublicAndEntitySpecificRoleList")
	@ResponseBody
	public ResponseEntity<?> getPublicAndEntitySpecificRoleList(@RequestParam("entityIdAndType") String entityIdAndType,
			@RequestParam(value = "selectedEntityIdAndType", required = false) String selectedEntityIdAndType) {
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			String entityIdDecode = new String(decoder.decode(entityIdAndType));
			JsonObject jsonObject = new JsonObject();
			if (selectedEntityIdAndType != null && !selectedEntityIdAndType.isEmpty()) {
				String selectedEntityIdAndTypeDecode = new String(decoder.decode(selectedEntityIdAndType));
				String[] split = selectedEntityIdAndTypeDecode.split("##");
				Long selectedEntityId = Long.parseLong(split[0]);
				String selectedEntityLevel = split[1];
				List<Role> selectedRoleList = roleService.getEntitySpecificSelectedRoleList(selectedEntityId,
						selectedEntityLevel);
				JsonArray jsonArray = new JsonArray();
				for (Role role : selectedRoleList) {
					JsonObject roleObject = new JsonObject();
					roleObject.addProperty("roleId", role.getRoleId());
					roleObject.addProperty("roleCode", role.getRoleCode());
					roleObject.addProperty("roleName", role.getDisplayName());
					jsonArray.add(roleObject);
				}
				jsonObject.addProperty("selectedRoleList", jsonArray.toString());
			} else {
				jsonObject.addProperty("selectedRoleList", "[]");
			}

			String[] split = entityIdDecode.split("##");
			Long entityId = Long.parseLong(split[0]);
			String entityLevel = split[1];
			List<Role> roleList = roleService.getEntitySpecificRoleList(entityId, entityLevel);
			JsonArray jsonArray = new JsonArray();
			for (Role role : roleList) {
				JsonObject roleObject = new JsonObject();
				roleObject.addProperty("roleId", role.getRoleId());
				roleObject.addProperty("roleCode", role.getRoleCode());
				roleObject.addProperty("roleName", role.getDisplayName());
				jsonArray.add(roleObject);
			}
			jsonObject.addProperty("roleList", jsonArray.toString());
			return new ResponseEntity<>(CommonHelperFunctions.onGsonObjectSuccess(jsonObject), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getRoleListByEntityId")
	@ResponseBody
	public ResponseEntity<?> getRoleListByEntityId(@RequestParam("entityId") String encodedEntityIds) {
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			List<RoleEntityCommonDto> allRoles = new ArrayList<>();
			String decodedEntityId = new String(decoder.decode(encodedEntityIds));

			String[] compaSpit = decodedEntityId.split(",");

			for (String data : compaSpit) {

				String[] split = data.split("##");
				long entityIdLong = Long.parseLong(split[0]);
				String entityLevel = split[1];

				List<RoleEntityCommonDto> rolesForEntity = umtConfigCoreFn.getRoleListByEntityId(entityIdLong,
						entityLevel);

				if (rolesForEntity != null && !rolesForEntity.isEmpty()) {
					allRoles.addAll(rolesForEntity);
				}
			}

			return new ResponseEntity<>(CommonHelperFunctions.onSuccess(allRoles), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// getHierarchyView
	@GetMapping("/getHierarchyView")
	@ResponseBody
	public ResponseEntity<?> getHierarchyView(@RequestParam("entityIdAndType") String entityIdAndType) {
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			String entityIdAndTypeStr = new String(decoder.decode(entityIdAndType));
			String[] split = entityIdAndTypeStr.split("##");
			Long entityId = Long.parseLong(split[0]);
			String entityLevel = split[1];
			return new ResponseEntity<>(CommonHelperFunctions
					.onGsonObjectSuccess(priviledgeService.getHierarchyView(entityId, entityLevel)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(CommonHelperFunctions.onError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
