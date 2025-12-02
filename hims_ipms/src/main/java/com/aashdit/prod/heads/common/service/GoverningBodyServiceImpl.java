package com.aashdit.prod.heads.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.prod.heads.common.controller.DownloadUploadController;
import com.aashdit.prod.heads.common.dto.GoverningBodyVo;
import com.aashdit.prod.heads.common.model.GoverningBody;
import com.aashdit.prod.heads.common.repository.GoverningBodyRepository;
import com.aashdit.prod.heads.common.utils.ApplicationConstants;
import com.aashdit.prod.heads.common.utils.LoggingUtil;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.ipms.model.ApplicationModuleMst;
import com.aashdit.prod.heads.hims.ipms.repository.ApplicationModuleMstRepository;
import com.aashdit.prod.heads.hims.umt.service.EntityLevelService;
import com.aashdit.prod.heads.hims.umt.service.RoleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GoverningBodyServiceImpl implements GoverningBodyService {

	private final GoverningBodyRepository governingBodyRepository;
	private final ApplicationModuleMstRepository applicationModuleMstRepository;
	private final EntityModuleMapService entityModuleMapService;
	private final EntityLevelService entityLevelService;
	private final DownloadUploadController downloadUploadController;
	private final RoleService roleService;
	private final MessageSource messageSource;

	@Override
	public Map<String, Object> getGoverningBodyLoadingData() {
		Map<String, Object> map = new HashMap<>();
		try {

			List<GoverningBody> governingBodies = getAllGoverningBodies(null);
			List<ApplicationModuleMst> modulList = applicationModuleMstRepository.findAllByIsActiveTrue();
			map.put("governingBodies", governingBodies);
			map.put("modulList", modulList);

		} catch (Exception e) {
			LoggingUtil.logError(e);
			e.printStackTrace();
		}

		return map;
	}

	@Override
	public List<GoverningBody> getAllGoverningBodies(Boolean isActive) {
		List<GoverningBody> governingBodies = new ArrayList<>();
		try {

			if (isActive == null) {
				governingBodies = governingBodyRepository.findAll();
			} else {
				governingBodies = governingBodyRepository.findAllByIsActive(isActive);
			}
		} catch (Exception e) {
			LoggingUtil.logError(e);
			e.printStackTrace();
		}
		return governingBodies;
	}

	@Override
	public Boolean checkDuplicateEmail(String email) throws Exception {
		try {
			Optional<GoverningBody> governingBody = governingBodyRepository.findByEmailId(email);
			return governingBody.isPresent();
		} catch (Exception e) {
			LoggingUtil.logError(e);
		}
		throw new Exception("Error in checking duplicate email");
	}

	@Override
	@Transactional
	public ServiceOutcome<GoverningBody> addOrUpdateGoverningBody(GoverningBodyVo governingBodyVo, MultipartFile icon) {
		ServiceOutcome<GoverningBody> outcome = new ServiceOutcome<>();
		boolean isForUserUpdate = false;
		try {
			GoverningBody governingBody = new GoverningBody();
			if (governingBodyVo.getGoverningBodyId() != null) {
				governingBody = getGoverningBodyById(governingBodyVo.getGoverningBodyId());
				isForUserUpdate = true;
			}
			if (governingBody == null) {
				outcome.setOutcome(false);
				outcome.setMessage("GoverningBody not found");
				return outcome;
			}
			governingBody.setGoverningBodyName(governingBodyVo.getGoverningBodyName());
			governingBody.setGoverningBodyCode(governingBodyVo.getGoverningBodyCode());
			governingBody.setIsActive(governingBodyVo.getIsActive());
			governingBody.setRemarks(governingBodyVo.getRemarks());
			governingBody.setAddressLine(governingBodyVo.getAddressLine());
			governingBody.setEmailId(governingBodyVo.getEmailId());
			governingBody.setPincode(governingBodyVo.getPincode());
			governingBody.setPhoneNo(governingBodyVo.getPhoneNo());
			governingBody.setWebsite(governingBodyVo.getWebsite());
			governingBody.setPrimaryRoleCode(governingBodyVo.getPrimaryRoleCode());
			if (icon != null && icon.getSize() > 0 && !icon.isEmpty()) {
				int lastDotIndex = icon.getOriginalFilename() == null ? -1
						: icon.getOriginalFilename().lastIndexOf('.');
				String fileName = icon.getOriginalFilename().substring(0, lastDotIndex);
				String fileExtension = icon.getOriginalFilename().substring(lastDotIndex + 1);
				String filePath = downloadUploadController.uploadFile(icon, "ICON", fileName, fileExtension);
				governingBody.setIcon(filePath);
			}
			governingBody = governingBodyRepository.save(governingBody);

			roleService.roleEntityMap(governingBodyVo.getOtherRoleCodes(), GoverningBody.class,
					governingBody.getGoverningBodyId());

			entityModuleMapService.entityModuleMap(governingBodyVo.getModuleIds(), governingBody.getGoverningBodyId(),
					GoverningBody.class);

			// userService.addUser
			entityLevelService.createUserAsPerNewEntityLevel(governingBody.getPrimaryRoleCode(),
					ApplicationConstants.LVL_CODE_GOVERNING, governingBody.getEmailId(),
					governingBody.getGoverningBodyName(), "N/A", governingBody.getPhoneNo(),
					governingBody.getGoverningBodyId(), GoverningBody.class.getSimpleName() + ".class",
					new Long[] { 1L }, governingBody.getGoverningBodyCode());

			outcome.setOutcome(true);
			outcome.setData(governingBody);
			outcome.setMessage(isForUserUpdate
					? messageSource.getMessage("corporate.msg.update", null, LocaleContextHolder.getLocale())
					: messageSource.getMessage("corporate.msg.success", null, LocaleContextHolder.getLocale()));
		} catch (Exception e) {
			LoggingUtil.logError(e);
			outcome.setOutcome(false);
			outcome.setMessage("Error saving Corporate");
		}
		return outcome;
	}

	@Override
	public GoverningBody getGoverningBodyById(Long governingBodyId) {
		try {
			return governingBodyRepository.findById(governingBodyId)
					.orElseThrow(() -> new IllegalArgumentException("GoverningBody not available for this given Id"));
		} catch (Exception e) {
			LoggingUtil.logError(e);
			return null;
		}
	}

	@Override
	public ServiceOutcome<Boolean> updateActiveStatusGoverningBody(Long governingBodyId, Boolean status) {
		String msg = "";
		ServiceOutcome<Boolean> svc = new ServiceOutcome<>();
		try {
			if (governingBodyId != null && status != null) {
				GoverningBody governingBody = getGoverningBodyById(governingBodyId);
				governingBody.setIsActive(status);
				governingBody = governingBodyRepository.save(governingBody);
				if (status) {
					msg = "GoverningBody activated sucessfully";
				} else {
					msg = "GoverningBody DeActivated sucessfully";
				}
				svc.setData(true);
				svc.setOutcome(true);
			} else {
				svc.setData(false);
				svc.setOutcome(false);

			}

		} catch (Exception e) {
			svc.setData(false);
			svc.setOutcome(false);
			msg = "Unable to update  GoverningBody status";
			LoggingUtil.logError(e);
		}
		svc.setMessage(msg);
		return svc;
	}

}
