package com.aashdit.prod.heads.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.prod.heads.common.controller.DownloadUploadController;
import com.aashdit.prod.heads.common.dto.MunicipalityVo;
import com.aashdit.prod.heads.common.model.District;
import com.aashdit.prod.heads.common.model.GoverningBody;
import com.aashdit.prod.heads.common.model.Municipality;
import com.aashdit.prod.heads.common.repository.MunicipalityRepository;
import com.aashdit.prod.heads.common.utils.ApplicationConstants;
import com.aashdit.prod.heads.common.utils.LoggingUtil;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.ipms.model.ApplicationModuleMst;
import com.aashdit.prod.heads.hims.ipms.repository.ApplicationModuleMstRepository;
import com.aashdit.prod.heads.hims.umt.service.EntityLevelService;
import com.aashdit.prod.heads.hims.umt.utils.SecurityHelper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final DistrictService districtService;
    private final ApplicationModuleMstRepository applicationModuleMstRepository;
    private final GoverningBodyService governingBodyService;
    private final EntityLevelService entityLevelService;
    private final EntityModuleMapService entityModuleMapService;
    private final DownloadUploadController downloadUploadController;

    @Override
    public Map<String, Object> getMunicipalitiesLoadingData(Long entityId, String level) {

        Map<String, Object> map = new HashMap<>();
        try {

            List<Municipality> municipalities = getAllMunicipalities(entityId, level);


            ServiceOutcome<List<District>> districties = districtService.getAllDistrict(true);


            List<ApplicationModuleMst> modulList = applicationModuleMstRepository.findAllByIsActiveTrue();

            List<GoverningBody> governingBodies = governingBodyService.getAllGoverningBodies(true);
            map.put("governingBodies", governingBodies);

            map.put("modulList", modulList);
            map.put("municipalities", municipalities);
            map.put("districties", districties.getData());
        } catch (Exception e) {
            LoggingUtil.logError(e);
            e.printStackTrace();
        }

        return map;
    }

    public List<Municipality> getAllMunicipalities(Long entityId, String level) {
        List<Municipality> municipalities = new ArrayList<>();
        try {
            municipalities = municipalityRepository.findByParent(entityId, level);
            if(municipalities.isEmpty()){
                if(level.equalsIgnoreCase("SUPERENTITY")) {
                    municipalities = municipalityRepository.findByGoverningBodyIdIsNull();
                }
            }
        } catch (Exception e) {
            LoggingUtil.logError(e);
            e.printStackTrace();
        }
        return municipalities;

    }

    @Override
    public List<Municipality> getAllMunicipalities(Boolean isIncludedFalse) {
        List<Municipality> municipalities = new ArrayList<>();
        try {
            Long entityId = SecurityHelper.getCurrentUserEntityId();
            String level = SecurityHelper.getCurrentUserEntityLevel();
            if (isIncludedFalse) {
                municipalities = municipalityRepository.findByParentAll(entityId, level);
            } else {
                municipalities = municipalityRepository.findByParent(entityId, level);
            }
        } catch (Exception e) {
            LoggingUtil.logError(e);
            e.printStackTrace();
        }
        return municipalities;

    }

    @SuppressWarnings("unused")
	@Override
    @Transactional
    public ServiceOutcome<Municipality> addOrUpdateMunicipality(MunicipalityVo municipalityVo, MultipartFile icon) {
        boolean isForUserUpdate = false;
        ServiceOutcome<Municipality> outcome = new ServiceOutcome<>();
        try {
            Municipality municipality = new Municipality();
            if (municipalityVo.getMunicipalityId() != null) {
                municipality = getMunicipalityById(municipalityVo.getMunicipalityId());
                isForUserUpdate = true;
            }
            if (municipality == null) {
                outcome.setOutcome(false);
                outcome.setMessage("Municipality not found");
                return outcome;
            }
            municipality.setMunicipalityName(municipalityVo.getMunicipalityName());
            municipality.setMunicipalityCode(municipalityVo.getMunicipalityCode());
            municipality.setDistrict(districtService.getById(municipalityVo.getDistrict()).getData());
            municipality.setIsActive(municipalityVo.getIsActive());
            municipality.setRemarks(municipalityVo.getRemarks());
            municipality.setAddressLine(municipalityVo.getAddressLine());
            municipality.setEmailId(municipalityVo.getEmailId());
            municipality.setPincode(municipalityVo.getPincode());
            municipality.setPhoneNo(municipalityVo.getPhoneNo());
            municipality.setWebsite(municipalityVo.getWebsite());
            String idAndType = municipalityVo.getObjectIdAndType();
            Long id = 0L;
            String type = "";
            if (idAndType != null && !idAndType.isEmpty()) {
                String[] idAndTypeArr = idAndType.split("##");
                if (idAndTypeArr.length == 2) {
                    id = Long.parseLong(idAndTypeArr[0]);
                    type = idAndTypeArr[1];
                }
            }

            municipality.setParentObjectId(id);
            municipality.setParentObjectType(type);
            municipality.setPrimaryRoleCode(municipalityVo.getPrimaryRoleCode());
            municipality.setGoverningBodyId(governingBodyService.getGoverningBodyById(id));

            if (icon != null && icon.getSize() > 0 && !icon.isEmpty()){
                int lastDotIndex = icon.getOriginalFilename() == null ? -1 : icon.getOriginalFilename().lastIndexOf('.');
                String fileName = icon.getOriginalFilename().substring(0, lastDotIndex);
                String fileExtension = icon.getOriginalFilename().substring(lastDotIndex + 1);
                String filePath = downloadUploadController.uploadFile(icon, "ICON", fileName, fileExtension);
                municipality.setIcon(filePath);
            }

            municipality = municipalityRepository.save(municipality);

            entityLevelService.createUserAsPerNewEntityLevel(municipality.getPrimaryRoleCode(), ApplicationConstants.LVL_CODE_MUNICIPALITY, municipality.getEmailId(), municipality.getMunicipalityName(), "N/A", municipality.getPhoneNo(), municipality.getMunicipalityId(), Municipality.class.getSimpleName()+".class", new Long[]{1L}, municipality.getMunicipalityCode());

            outcome.setOutcome(true);
            outcome.setData(municipality);
            outcome.setMessage("Municipality saved successfully");

            entityModuleMapService.entityModuleMap(municipalityVo.getModuleIds(), municipality.getMunicipalityId(), Municipality.class);


        } catch (Exception e) {
            LoggingUtil.logError(e);
            outcome.setOutcome(false);
            outcome.setMessage("Error saving municipality");
        }
        return outcome;

    }


    @Override
    public Municipality getMunicipalityById(Long id) {
        try {
            return municipalityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Municipality not available for this given Id"));
        } catch (Exception e) {
            LoggingUtil.logError(e);
            return null;
        }
    }

    @Override
    public ServiceOutcome<Boolean> updateActiveStatusMunicipality(Long municipalityId, Boolean status) {
        String msg = "";
        ServiceOutcome<Boolean> svc = new ServiceOutcome<>();
        try {
            if (municipalityId != null && status != null) {
                Municipality municipality = getMunicipalityById(municipalityId);
                municipality.setIsActive(status);
                municipalityRepository.save(municipality);
                if (status) {
                    msg = "Municipality activated successfully";
                } else {
                    msg = "Municipality DeActivated successfully";
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
            msg = "Unable to update  muncipality status";
            LoggingUtil.logError(e);
        }
        svc.setMessage(msg);
        return svc;
    }

    @Override
    public List<Municipality> getAllMunicipalitiesByGovId(Long govId, Boolean isActive) {
        List<Municipality> municipalities = new ArrayList<>();
        try {

            if (isActive == null) {
                municipalities = municipalityRepository.findAllByGoverningBodyIdGoverningBodyId(govId);
            } else {
                municipalities = municipalityRepository.findAllByGoverningBodyIdGoverningBodyIdAndIsActive(govId, isActive);
            }
        } catch (Exception e) {
            LoggingUtil.logError(e);
            e.printStackTrace();
        }
        return municipalities;

    }

    @Override
    public List<Municipality> getAllMunicipalitiesByParentIdAndParentLevel(Long entityId, String entityType) {
        List<Municipality> municipalities = new ArrayList<>();
        try{
        municipalities = municipalityRepository.findByParentAll(entityId, entityType);
        }catch (Exception e){
            log.error("Error getting municipality Lists at MunicipalityServiceImpl::getAllMunicipalitiesByParentId("+entityId +"&" + entityType+ ")", e);
        }
        return municipalities;
    }
}
