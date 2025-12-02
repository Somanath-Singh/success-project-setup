package com.aashdit.prod.heads.common.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.prod.heads.common.dto.GoverningBodyVo;
import com.aashdit.prod.heads.common.model.GoverningBody;
import com.aashdit.prod.heads.common.service.EntityModuleMapService;
import com.aashdit.prod.heads.common.service.GoverningBodyService;
import com.aashdit.prod.heads.common.utils.DataDecryption;
import com.aashdit.prod.heads.common.utils.LoggingUtil;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.umt.model.Role;
import com.aashdit.prod.heads.hims.umt.model.RoleEntityMap;
import com.aashdit.prod.heads.hims.umt.model.RoleRightLevelMaster;
import com.aashdit.prod.heads.hims.umt.repository.RoleEntityMapRepository;
import com.aashdit.prod.heads.hims.umt.repository.RoleRightLevelMasterRepository;
import com.aashdit.prod.heads.hims.umt.service.RoleService;
import com.aashdit.prod.heads.hims.umt.specs.UmtConfigCoreFn;
import com.google.gson.Gson;

@Controller
@RequestMapping("/govern")
public class GoverningBodyOnboardingController {

	@Autowired
	private GoverningBodyService governingBodyService;

	@Autowired
	private EntityModuleMapService entityModuleMapService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleEntityMapRepository roleEntityMapRepository;

	@Autowired
	private UmtConfigCoreFn umtConfigCoreFn;
	@Autowired
	private RoleRightLevelMasterRepository roleRightLevelMasterRepository;

	@GetMapping("/add")
	public String loadGoverningBodyForm(Model model, HttpServletRequest request) {
		try {
			Map<String, Object> governingBody = governingBodyService.getGoverningBodyLoadingData();
			model.addAttribute("data", governingBody);
			ServiceOutcome<List<Role>> allRoles = roleService.getAllRoles(false);
			List<Role> roleList = new ArrayList<>();
			if (allRoles.getOutcome() && allRoles.getData() != null && !allRoles.getData().isEmpty()) {
				roleList = allRoles.getData();
			}
			model.addAttribute("roleList", roleList);

		} catch (Exception e) {
			LoggingUtil.logError(e);
			e.printStackTrace();
		}
		return "governingBody-form";
	}

	// check duplicate email
	@GetMapping("/check-duplicate-email")
	@ResponseBody
	public ResponseEntity<?> checkDuplicateEmail(@RequestParam String encodedData) {
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			String decode = new String(decoder.decode(encodedData));
			Gson gson = new Gson();
			String email = gson.fromJson(decode, String.class);
			Boolean isDuplicate = governingBodyService.checkDuplicateEmail(email);
			return ResponseEntity.ok(isDuplicate);
		} catch (Exception e) {
			LoggingUtil.logError(e);
			return ResponseEntity.badRequest().body(false);
		}
	}

	@PostMapping("/save")
	public String addGoverningBody(String encodedData, RedirectAttributes attr,
			@RequestParam(value = "icon", required = false) MultipartFile icon) {

		try {
			GoverningBodyVo governingBodyVo = DataDecryption.decryptData(encodedData, GoverningBodyVo.class, true, 0);
			ServiceOutcome<GoverningBody> svc = governingBodyService.addOrUpdateGoverningBody(governingBodyVo, icon);
			attr.addFlashAttribute(svc.getOutcome() ? "success_msg" : "error_msg", svc.getMessage());
		} catch (Exception e) {
			LoggingUtil.logError(e);
			e.printStackTrace();
		}
		return "redirect:/govern/add";
	}

	@GetMapping("/edit")
	public String updateGoverningBody(String encodedData, RedirectAttributes attr) {
		Long governingBodyId = null;

		try {
			JSONObject json = DataDecryption.decryptData(encodedData, JSONObject.class, true, 0);
			governingBodyId = json != null && json.get("governingBodyIdHd") != null
					&& !json.get("governingBodyIdHd").toString().isEmpty()
							? Long.valueOf(json.get("governingBodyIdHd").toString())
							: null;
			GoverningBody governingBody = governingBodyService.getGoverningBodyById(governingBodyId);
			List<Long> moduleIds = entityModuleMapService.getAppModuleIdByEntityIdAndCode(governingBodyId,
					GoverningBody.class);
			governingBody.setModuleIds(moduleIds);
			attr.addFlashAttribute("governingBody", governingBody);
			attr.addFlashAttribute("roleDetails", umtConfigCoreFn.makingRoleDetails(governingBody.getGoverningBodyId(),
					governingBody.getPrimaryRoleCode(), GoverningBody.class));
			Optional<RoleRightLevelMaster> roleRi8Lvl = roleRightLevelMasterRepository
					.findByLevelKeyClass(GoverningBody.class.getSimpleName() + ".class");
			String roleRightLevel = roleRi8Lvl.isEmpty() ? "" : roleRi8Lvl.get().getLevelCode();
			List<RoleEntityMap> entityMapList = roleEntityMapRepository.findByEntityIdAndLevel(governingBodyId,
					roleRightLevel);
			if (!entityMapList.isEmpty()) {
				List<String> otherRoleCodes = entityMapList.stream().map(RoleEntityMap::getRoleCode)
						.collect(Collectors.toList());
				attr.addFlashAttribute("entityMapList", otherRoleCodes);
			}

		} catch (Exception e) {
			LoggingUtil.logError(e);
			e.printStackTrace();
		}
		return "redirect:/govern/add";
	}

	@PostMapping("/update-status")
	public String updateActiveStatusGoverningBody(String encodedData, RedirectAttributes attr) {
		Long governingBodyId = null;

		try {
			JSONObject json = DataDecryption.decryptData(encodedData, JSONObject.class, true, 0);
			governingBodyId = json != null && json.get("governingBodyIdd") != null
					&& !json.get("governingBodyIdd").toString().isEmpty()
							? Long.valueOf(json.get("governingBodyIdd").toString())
							: null;
			Boolean status = null;
			status = json != null && json.get("status") != null && !json.get("status").toString().isEmpty()
					? Boolean.valueOf(json.get("status").toString())
					: null;

			ServiceOutcome<Boolean> svc = governingBodyService.updateActiveStatusGoverningBody(governingBodyId, status);
			attr.addFlashAttribute(svc.getOutcome() ? "success_msg" : "error_msg", svc.getMessage());

		} catch (Exception e) {
			LoggingUtil.logError(e);
			e.printStackTrace();
		}
		return "redirect:/govern/add";
	}

}
