package com.aashdit.prod.heads.common.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aashdit.prod.heads.common.model.BankBranchMaster;
import com.aashdit.prod.heads.common.service.CommonService;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.umt.dto.EntityIdAndUserLevel;
import com.aashdit.prod.heads.hims.umt.model.OrganizationStructureHierarchy;
import com.aashdit.prod.heads.hims.umt.model.Role;


@RestController
@RequestMapping("/api")
public class CommonAjaxController {
	
	@Autowired
	private CommonService commonService;
	
	
	@GetMapping("/allow/getBranchBybankId")
    public String getBranchByBankId(@RequestParam Long bankId) {
        JSONObject obj = new JSONObject();
        try {
        	ServiceOutcome<List<BankBranchMaster>> branches = commonService.findByBankId(bankId);
            if (branches != null && !branches.getData().isEmpty()) {
                JSONArray branchArray = new JSONArray();
                for (BankBranchMaster branch : branches.getData()) {
                    JSONObject branchObj = new JSONObject();
                    branchObj.put("bankBranchId", branch.getBankBranchId());
                    branchObj.put("branchName", branch.getBranchName());
                    branchArray.add(branchObj);
                }
                obj.put("data", branchArray);
            } else {
                obj.put("message", "No branches found for the given bank.");
            }
        } catch (Exception e) {
            obj.put("error", "An error occurred while fetching branches: " + e.getMessage());
        }
        return obj.toString();
    }
	
	
	 @GetMapping("allow/getIfscByBranchId")
	 public String getIfscByBranchId(@RequestParam Long branchId) {
	     JSONObject obj = new JSONObject();
	     try {
	         BankBranchMaster branch = commonService.getIfscCodeByBranchId(branchId);
	         if (branch != null) {
	             JSONObject branchObj = new JSONObject();
	             branchObj.put("bankBranchId", branch.getBankBranchId());
	             branchObj.put("ifscCode", branch.getIfscCode()); // Assuming ifscCode is the correct property
	             obj.put("data", branchObj);
	         } else {
	             obj.put("message", "No branch found for the given ID.");
	         }
	     } catch (Exception e) {
	         obj.put("error", "An error occurred while fetching branch: " + e.getMessage());
	     }
	     return obj.toString();
	 }

	@GetMapping("/allow/getLevelListByOrganization")
	public String getDepartmentListByOfficeId(String orgData) {
		JSONArray arr = new JSONArray();
		try {
			List<OrganizationStructureHierarchy> list = commonService.getLevelListByOrganization(orgData);
			if (list != null && !list.isEmpty()) {
				for(OrganizationStructureHierarchy org : list) {
					JSONObject deptObj = new JSONObject();
					deptObj.put("levelId", org.getId());
					deptObj.put("levelCode", org.getLevelCode());
					deptObj.put("orgIdNlevelCode", org.getOrganizationStructure().getObjectId()+"##"+org.getLevelCode()+"##"+org.getOrganizationStructure().getObjectType());
					arr.add(deptObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

	@GetMapping("/allow/getActualLevelNameListByLevelCodeAndOrgId")
	public String getActualLevelNameListByLevelCodeAndOrgId(String levelData) {
		JSONArray arr = new JSONArray();
		try {
			List<EntityIdAndUserLevel> list = commonService.getActualLevelNameListByLevelCodeAndOrgId(levelData);
			if (list != null && !list.isEmpty()) {
				for(EntityIdAndUserLevel entityData : list) {
					JSONObject deptObj = new JSONObject();
					deptObj.put("entityId", entityData.getEntityId());
					deptObj.put("entityName", entityData.getOrganizationName());
					deptObj.put("entityIdNEntityName", entityData.getCombineTwo());
					arr.add(deptObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

	@GetMapping("/allow/getRoleListByActualLevel")
	public String getRoleListByActualLevel(String actualLevelData) {
		JSONArray arr = new JSONArray();
		try {
			List<Role> list = commonService.getRoleListByActualLevel(actualLevelData);
			if (list != null && !list.isEmpty()) {
				for(Role entityData : list) {
					JSONObject rolObj = new JSONObject();
					rolObj.put("entityId", entityData.getRoleId());
					rolObj.put("entityName", entityData.getDisplayName());
					arr.add(rolObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr.toString();
	}
	 

}
