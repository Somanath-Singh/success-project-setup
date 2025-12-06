package com.aashdit.setup.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aashdit.setup.common.dto.ItemOfWorkDto;
import com.aashdit.setup.common.service.ItemOfWorkService;
import com.aashdit.setup.common.utils.FormDecryptionUtil;
import com.aashdit.setup.core.ServiceOutcome;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/itemOfWork")
public class ItemOfWorkController {
	
	/**
	 * Handles itemOfWork logic.
	 *
	 * @author Somanath Singh
	 * @since 06/09/2025
	 */

	@Autowired
	private ItemOfWorkService itemOfWorkService;

	@GetMapping("/add")
	public String addItemOfWork(Model model) {
		try {
			ServiceOutcome<List<ItemOfWorkDto>> itemList = itemOfWorkService.getAllActiveItemsOfWork();
			model.addAttribute("itemList", itemList.getData());
		} catch (Exception e) {
			log.error("Exception occured in addItemOfWork()  of ItemOfWorkController : " + e.getMessage());
			e.printStackTrace();
		}
		return "item.of.work";
	}

	@PostMapping("/save")
	public String saveItemOfWork(HttpServletRequest request, RedirectAttributes attributes, String cipherText) {
		try {
			ItemOfWorkDto dto = FormDecryptionUtil.decryptAndDeserializeForm(cipherText, request, ItemOfWorkDto.class);
			ServiceOutcome<Boolean> outcome = itemOfWorkService.saveOrUpdateItemOfWork(dto);

			if (outcome.getOutcome()) {
				attributes.addFlashAttribute("success_msg", outcome.getMessage());
			} else {
				attributes.addFlashAttribute("error_msg", outcome.getMessage());
			}
		} catch (Exception e) {
			log.error("Exception occured in saveItemOfWork()  of ItemOfWorkController : " + e.getMessage());
			e.printStackTrace();
			attributes.addFlashAttribute("error_msg", "Something went wrong while saving item.");
		}

		return "redirect:/itemOfWork/add";
	}

	@PostMapping("/edit")
	public String editItemOfWork(HttpServletRequest request, RedirectAttributes attributes, String cipherText) {
		try {
			Long itemId = FormDecryptionUtil.decryptAndExtractId(cipherText, request, "id");

			ServiceOutcome<ItemOfWorkDto> item = itemOfWorkService.getActiveItemOfWorkById(itemId);
			attributes.addFlashAttribute("item", item.getData());

		} catch (Exception e) {
			log.error("Exception occured in editItemOfWork()  of ItemOfWorkController : " + e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/itemOfWork/add";
	}
	
	@ResponseBody
	@GetMapping("/validate-name")
	public ResponseEntity<Boolean> validateName(@RequestParam String name, @RequestParam(required = false) Long id) {
		try {
			ServiceOutcome<Boolean> outcome = itemOfWorkService.checkDuplicateName(name, id);
			return ResponseEntity.ok(outcome.getData());
		} catch (Exception e) {
			log.error("Exception occurred in validateName() in ItemOfWorkController => {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@ResponseBody
	@GetMapping("/toggle-status")
	public ResponseEntity<Boolean> toggleStatus(@RequestParam Long id) {
		try {
			ServiceOutcome<Boolean> outcome = itemOfWorkService.validateWorkItemStatus(id);
			return ResponseEntity.ok(outcome.getData());
		} catch (Exception e) {
			log.error("Exception occurred in toggleStatus() in ItemOfWorkController => {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
}
