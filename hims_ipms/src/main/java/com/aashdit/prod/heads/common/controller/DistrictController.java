package com.aashdit.prod.heads.common.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import com.aashdit.prod.heads.common.dto.DuplicateCheckDto;
import com.aashdit.prod.heads.common.model.District;
import com.aashdit.prod.heads.common.model.State;
import com.aashdit.prod.heads.common.service.CommonValidationService;
import com.aashdit.prod.heads.common.service.DistrictService;
import com.aashdit.prod.heads.common.service.StateService;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;

@Controller
@RequestMapping(value = "/admin/district")
public class DistrictController {

	final static Logger logger = Logger.getLogger(DistrictController.class);

	@Autowired
	private DistrictService districtService;

	@Autowired
	private StateService stateService;

	@Autowired
	private CommonValidationService commonValidationService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/list", name = "List District")
	public String list(Model model, @RequestParam(value = "showDeleted", required = false) Boolean showDeleted) {

		try {
			ServiceOutcome<List<District>> serviceOutcome = districtService.getAllDistrict(true);
			if (serviceOutcome.getOutcome()) {
				model.addAttribute("districtList", serviceOutcome.getData());
			} else {
				model.addAttribute("error_msg", serviceOutcome.getMessage());
			}
		} catch (Exception ex) {
			logger.error(ex);
			model.addAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

		}

		return "admin.district.list";
	}

	@GetMapping(path = "/add", name = "Add District")
	public String add(Model model) {
		try {
			ServiceOutcome<List<State>> staOutcome = stateService.getAllState(true);
			if (staOutcome.getOutcome()) {
				model.addAttribute("stateList", staOutcome.getData());
			} else {
				model.addAttribute("error_msg", staOutcome.getMessage());
			}

		} catch (Exception ex) {
			logger.error(ex);
			model.addAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

		}

		return "admin.district.add";
	}

	@GetMapping(path = "/edit/{districtId}", name = "Edit District")
	public String edit(Model model, @PathVariable("districtId") Long districtId,
			RedirectAttributes redirectAttributes) {
		try {
			ServiceOutcome<District> serviceOutcome = districtService.getById(districtId);
			ServiceOutcome<List<State>> staOutcome = stateService.getAllState(true);
			if (serviceOutcome.getOutcome()) {
				model.addAttribute("districtData", serviceOutcome.getData());
				model.addAttribute("stateList", staOutcome.getData());
			} else {
				model.addAttribute("error_msg", serviceOutcome.getMessage());
			}

		} catch (Exception ex) {
			logger.error(ex);
			model.addAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));

		}
		return "admin.district.add";
	}

	/**
	 * purpose:This method used to validate unique code for district since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/validateDistrictCode", name = "Validate district code")
	public @ResponseBody DuplicateCheckDto validateDistrictCode(String districtCode, Long districtId, String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonValidationService
					.checkDuplicateByAnyCode(districtCode.trim(), districtId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			logger.error(ex);
		}

		return duplicateCheckDto;
	}

	/**
	 * purpose:This method used to validate unique name for district since :
	 * 11/11/2020
	 */
	@GetMapping(path = "/validateDistrictName", name = "Validate district Name")
	public @ResponseBody DuplicateCheckDto validateDistrictName(String districtName, Long districtId, String type) {
		DuplicateCheckDto duplicateCheckDto = null;
		try {
			ServiceOutcome<DuplicateCheckDto> srvOutcome = commonValidationService
					.checkDuplicateByAnyName(districtName.trim(), districtId, type);
			if (srvOutcome.getOutcome()) {
				duplicateCheckDto = srvOutcome.getData();
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		return duplicateCheckDto;
	}

	@PostMapping(value = "/save", name = "Save District")
	public String saveDistrict(@ModelAttribute("district") District district, RedirectAttributes attributes) {
		try {
			ServiceOutcome<District> serviceOutcome = districtService.saveDistrict(district);
			if (serviceOutcome.getOutcome()) {
				attributes.addFlashAttribute("success_msg", serviceOutcome.getMessage());
			} else {
				attributes.addFlashAttribute("error_msg", serviceOutcome.getMessage());
			}

		} catch (Exception ex) {
			logger.error(ex);
			attributes.addFlashAttribute("error_msg",
					messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return "redirect:/admin/district/list";
	}

}
