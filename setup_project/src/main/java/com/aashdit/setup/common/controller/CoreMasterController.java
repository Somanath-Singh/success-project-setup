package com.aashdit.setup.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.setup.common.dto.BankBranchDTO;
import com.aashdit.setup.common.dto.FinancialYearMasterDto;
import com.aashdit.setup.common.dto.VendorDocuments;
import com.aashdit.setup.common.model.AssetVendor;
import com.aashdit.setup.common.model.BankBranchMaster;
import com.aashdit.setup.common.model.Category;
import com.aashdit.setup.common.model.FinancialYear;
import com.aashdit.setup.common.model.VendorType;
import com.aashdit.setup.common.service.CommonService;
import com.aashdit.setup.common.service.CoreMasterService;
import com.aashdit.setup.common.utils.DataDecryption;
import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.specs.UmtConfigCoreFn;
import com.aashdit.setup.umt.utils.CommonUMTConstants;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mst")
public class CoreMasterController {

	@Autowired
	private CoreMasterService coreMasterService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UmtConfigCoreFn umtConfigCoreFn;

	@GetMapping("/addFinancialYear")
	public String addFinancialYear(Model model) {
		ServiceOutcome<List<FinancialYear>> outcome = new ServiceOutcome<>();
		try {
			outcome = coreMasterService.getAllFinancialYear();
			model.addAttribute("finYearList", outcome.getData());
		} catch (Exception e) {
			log.error("Exception occured in CoreMasterController at addFinancialYear Method ---->" + e);
		}
		return "site.financialYearMst";
	}

	@PostMapping("/saveFinancialYear")
	public String saveFinancialYear(FinancialYearMasterDto financialYear, RedirectAttributes attr) {
		ServiceOutcome<FinancialYear> outCome = null;
		try {
			outCome = coreMasterService.saveFinancialYear(financialYear);
			attr.addFlashAttribute(outCome.getOutcome() ? "success_msg" : "error_msg", outCome.getMessage());
		} catch (Exception e) {
			log.error("Exception occured in CoreMasterController at saveFinancialYear Method ---->" + e);
		}
		return "redirect:/mst/addFinancialYear";
	}

	@GetMapping("/editFinancialYear")
	public String editFinancialYear(Long finYrId, String finYearId, RedirectAttributes attr,
			HttpServletRequest request) {
		try {
			ServiceOutcome<FinancialYear> outCome = coreMasterService.editFinancialYear(finYrId);

			attr.addFlashAttribute("finYearData", outCome.getData());
		} catch (Exception e) {
			log.error("Exception occured in CoreMasterController at editFinancialYear Method ---->" + e);
		}
		return "redirect:/mst/addFinancialYear";
	}

	@ResponseBody
	@GetMapping(path = "/duplicate_fynYear")
	public String checkFinYear(String finYear) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<FinancialYear> fyr = coreMasterService.findByFynYear(finYear);
		if (fyr.size() > 0) {
			jsonObject.put("isDuplicate", true);
			jsonObject.put("fynYear", fyr.get(0).getFinYear());
		} else {
			jsonObject.put("isDuplicate", false);
		}
		return jsonObject.toString();

	}

//	###################### Bank Branch Master Start ########################

	@GetMapping("/addBankBranch")
	public String addBankBranch(Model model) {
		try {
			model.addAttribute("branchList", coreMasterService.getAllBankBranchList());
			model.addAttribute("bankList", coreMasterService.getAllBankList());
		} catch (Exception e) {
			log.error("Exception occured in CoreMasterController at addBankBranch Method ---->" + e);
		}
		return "site.bankBranchMst";
	}

	@PostMapping("/saveBankBranch")
	public String saveBankBranch(BankBranchDTO bankBranchDto, RedirectAttributes attr) {
		ServiceOutcome<BankBranchMaster> outCome = null;
		try {
			outCome = coreMasterService.saveBankBranch(bankBranchDto);
			attr.addFlashAttribute(outCome.getOutcome() ? "success_msg" : "error_msg", outCome.getMessage());
		} catch (Exception e) {
			log.error("Exception occured in CoreMasterController at saveBankBranch Method ---->" + e);
		}
		return "redirect:/mst/addBankBranch";
	}

	@GetMapping("/editBankBranch")
	public String editBankBranch(Long bankBranchId, RedirectAttributes attr, HttpServletRequest request) {
		try {
			ServiceOutcome<BankBranchMaster> outCome = coreMasterService.editBanBranch(bankBranchId);

			attr.addFlashAttribute("branchMstData", outCome.getData());
		} catch (Exception e) {
			log.error("Exception occured in CoreMasterController at editBankBranch Method ---->" + e);
		}
		return "redirect:/mst/addBankBranch";
	}

	@ResponseBody
	@GetMapping(path = "/duplicate_ifscCode")
	public String checkIfscCode(String ifscCode) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<BankBranchMaster> branchIfsc = coreMasterService.findByIfscCode(ifscCode);
		if (branchIfsc.size() > 0) {
			jsonObject.put("isDuplicate", true);
			jsonObject.put("ifscCode", branchIfsc.get(0).getIfscCode());
		} else {
			jsonObject.put("isDuplicate", false);
		}
		return jsonObject.toString();

	}
//	###################### Bank Branch Master End ########################

//	Sourava Pattanayak 16-11-2023
//	view page of add category
	@GetMapping(value = "/category/add", name = "Add Category")
	public String addCategory(Model model,
			@RequestParam(value = "encodedDataedit", required = false) String encodedDataedit) {
		org.json.simple.JSONObject json = DataDecryption.decryptData(encodedDataedit, org.json.simple.JSONObject.class,
				true, 0);
		Long categoryId = null;
		if (json != null) {
			categoryId = json.get("categoryId") != null && !json.get("categoryId").toString().isEmpty()
					? Long.parseLong(json.get("categoryId").toString())
					: null;
		}
		ServiceOutcome<List<Category>> catList = commonService.getAllCategory();
		model.addAttribute("catList", catList.getData());

		if (categoryId != null) {
			Category cat = commonService.getCategoryById(categoryId);
			model.addAttribute("cat", cat);
		}

		return "site.master.category";
	}

//	Sourava Pattanayak 16-11-2023
//	save of add category
	@PostMapping(value = "/category/save", name = "Save And Update Category")
	public String saveCategory(Model model, String encodedDataFrm, RedirectAttributes attr) {
		try {
			Category category = DataDecryption.decryptData(encodedDataFrm, Category.class, true, 0);
			ServiceOutcome<Category> result = commonService.saveAndUpdateCategory(category);
			if (result.getOutcome()) {
				attr.addFlashAttribute("success_msg", result.getMessage());
			} else {
				attr.addFlashAttribute("error_msg", result.getMessage());
			}
		} catch (Exception e) {
			attr.addFlashAttribute("error_msg", "Sorry ! Something went wrong");
		}
		return "redirect:/mst/category/add";
	}

//	Sourava Pattanayak 17-11-2023
//	Remove category
	@RequestMapping(value = "/category/delete", name = "Remove Category", method = RequestMethod.POST)
	public String removeCategory(RedirectAttributes attr, @RequestParam(name = "categoryId") Long categoryId) {
		Category cat = commonService.deleteCategory(categoryId);
		if (cat != null) {
			attr.addFlashAttribute("success_msg", "Category Removed Successfully");
		} else {
			attr.addFlashAttribute("error_msg", "Error in Category Remove Process");
		}
		return "redirect:/mst/category/add";
	}

//	Sourava Pattanayak 21-11-2023
//	view page of add Vendor
	@GetMapping(value = "/vendor/add", name = "Add Vendor")
	public String addVendor(Model model,
			@RequestParam(value = "encodedDataedit", required = false) String encodedDataedit,
			@RequestParam(required = false) String filterData,
			@RequestParam(required = false) String parentEntityData) {

		Map<String, Object> idAndLevel = UmtConfigCoreFn.updateEntityIdAndLevel(filterData);
		Long id = (Long) idAndLevel.get(CommonUMTConstants.USER_ENTITY_ID_KEY);
		String type = idAndLevel.get(CommonUMTConstants.USER_ENTITY_LEVEL_KEY).toString();

		org.json.simple.JSONObject jsonObject = DataDecryption.decryptData(encodedDataedit,
				org.json.simple.JSONObject.class, true, 0);
		Long vendorId = null;
		if (jsonObject != null) {
			vendorId = jsonObject.get("vendorId") != null && !jsonObject.get("vendorId").toString().isEmpty()
					? Long.parseLong(jsonObject.get("vendorId").toString())
					: null;
		}
		ServiceOutcome<List<Category>> catList = commonService.getAllActiveCategory();
		model.addAttribute("catList", catList.getData());

		List<VendorType> lstVndTyp = commonService.getAllActiveVendorType();
		model.addAttribute("lstVndTyp", lstVndTyp);

		List<AssetVendor> assetVendorList = commonService.getAllVendorNameAsc(id, type);
		model.addAttribute("vendorList", assetVendorList);

		if (vendorId != null) {
			AssetVendor assetVendor = commonService.getVendorById(vendorId);
			model.addAttribute("vendor", assetVendor);
			this.umtConfigCoreFn.addObjectIdAndTypeToModelWithData(model, assetVendor.getObjectIdAndType());
			this.umtConfigCoreFn.addEntityIdAndLevelToModelWithData(model, AssetVendor.class, true,
					assetVendor.getObjectIdAndType(), false, true);
		}

		umtConfigCoreFn.addEntityIdAndLevelToModelWithData(model, AssetVendor.class, true, "", false, true);
		umtConfigCoreFn.addEntityIdAndLevelToModelForList(model, parentEntityData, AssetVendor.class);
		model.addAttribute("selectedFilterEntityDataCombo",
				idAndLevel.get(CommonUMTConstants.USER_ENTITY_ID_AND_LEVEL_KEY));

		return "site.master.assetVendor";
	}

//	Sourava Pattanayak 21-11-2023
//	save of add Vendor
	@PostMapping(value = "/vendor/save", name = "Save And Update Vendor")
	public String saveVendor(Model model, @RequestParam("encodedDataFrm") String encodedDataFrm,
			VendorDocuments vendorDoc, RedirectAttributes attr) {
		try {
			AssetVendor assetVendor = DataDecryption.decryptData(encodedDataFrm, AssetVendor.class, true, 0);
			ServiceOutcome<AssetVendor> result = commonService.saveAndUpdateVendor(assetVendor, vendorDoc);
			if (result.getOutcome()) {
				attr.addFlashAttribute("success_msg", result.getMessage());
			} else {
				attr.addFlashAttribute("error_msg", result.getMessage());
			}
		} catch (Exception e) {
			attr.addFlashAttribute("error_msg", "Sorry ! Something went wrong");
		}
		return "redirect:/mst/vendor/add";
	}

	// Sourava Pattanayak 21-11-2023
//	Remove Vendor
	@PostMapping(value = "/vendor/delete", name = "Remove Vendor")
	public String removeVendor(RedirectAttributes attr, Long vendorId) {
		AssetVendor assetVendor = commonService.deleteVendor(vendorId);
		if (assetVendor != null) {
			attr.addFlashAttribute("success_msg", "Vendor Removed Successfully");
		} else {
			attr.addFlashAttribute("error_msg", "Error in Vendor Remove Process");
		}
		return "redirect:/mst/vendor/add";
	}

}
