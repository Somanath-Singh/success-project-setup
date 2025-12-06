package com.aashdit.setup.common.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.setup.common.dto.LookupValueDTO;
import com.aashdit.setup.common.model.Block;
import com.aashdit.setup.common.model.CommonLookupValue;
import com.aashdit.setup.common.model.District;
import com.aashdit.setup.common.model.FinancialYear;
import com.aashdit.setup.common.model.Grampanchayat;
import com.aashdit.setup.common.model.Municipality;
import com.aashdit.setup.common.model.State;
import com.aashdit.setup.common.model.Village;
import com.aashdit.setup.common.model.Ward;
import com.aashdit.setup.common.repository.BlockRepository;
import com.aashdit.setup.common.repository.CommonLookupValueRepository;
import com.aashdit.setup.common.repository.DistrictRepository;
import com.aashdit.setup.common.repository.FinancialYearRepository;
import com.aashdit.setup.common.repository.GrampanchayatRepository;
import com.aashdit.setup.common.repository.MunicipalityRepository;
import com.aashdit.setup.common.repository.StateRepository;
import com.aashdit.setup.common.repository.VillageRepository;
import com.aashdit.setup.common.repository.WardRepository;
import com.aashdit.setup.common.utils.LookupValueMapper;
import com.aashdit.setup.common.utils.RandomNumberGenerate;
import com.aashdit.setup.common.utils.UploadFile;
import com.aashdit.setup.core.ServiceOutcome;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MasterCommonServiceImpl implements MasterCommonService {
	
	@Autowired
    private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private BlockRepository blockRepository;

	@Autowired
	private GrampanchayatRepository GrampanchayatRepository;
	
	@Autowired
	private VillageRepository villageRepository;
	
	@Autowired
	private MunicipalityRepository municipalityRepository;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Autowired
	private FinancialYearRepository financialYearRepository;
	
	@Autowired
    private LookupValueMapper lookupValueMapper;
	
	@Autowired
	private CommonLookupValueRepository commonLookupValueRepository;
	
    ResourceBundle rb = ResourceBundle.getBundle("application");
	
	@Override
    public ServiceOutcome<List<State>> getAllActiveStatesIdAndName() {
        ServiceOutcome<List<State>> outcome = new ServiceOutcome<>();
        try {
            List<State> states = stateRepository.findAllByIsActiveTrue()
                    .stream()
                    .map(entity -> {
                        State dto = new State();
                        dto.setStateId(entity.getStateId());
                        dto.setStateName(entity.getStateName());
                        return dto;
                    })
                    .collect(Collectors.toList());
            outcome.setData(states);
            outcome.setOutcome(true);
            outcome.setMessage("States fetched successfully");
        } catch (Exception e) {
        	log.error("Exception occured in getAllActiveStatesIdAndName() of MasterCommonServiceImpl" + e.getMessage());
        	e.printStackTrace();
        	outcome.setData(List.of());
            outcome.setOutcome(false);
            outcome.setMessage("Failed to fetch states: " + e.getMessage());
        }
        return outcome;
    }
  
  	@Override
	public ServiceOutcome<List<District>> getAllActiveDistrictIdAndName() {
		ServiceOutcome<List<District>> outcome = new ServiceOutcome<>();
		try {
			List<District> districtDropDownList = districtRepository.findAllByIsActiveTrue();
			List<District> DistrictList = districtDropDownList.stream().map(district -> {
				District dto = new District();
				dto.setDistrictId(district.getDistrictId());
				dto.setDistrictName(district.getDistrictName());
				return dto;
			}).collect(Collectors.toList());

			outcome.setData(DistrictList);
		} catch (Exception e) {
			outcome.setData(null);
			log.error("Exception occured in getAllActiveDistrictIdAndName() of MasterCommonServiceImpl"+ e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	}
  
    @Override
    public ServiceOutcome<List<District>> getAllActiveDistrictsIdAndNameByState(Long stateId) {
        ServiceOutcome<List<District>> outcome = new ServiceOutcome<>();
        try {
        	List<District> districtsList = districtRepository.findAllByStateIdStateIdAndIsActiveTrue(stateId);
            List<District> districtDropDownList = districtsList.stream()
                    .map(entity -> {
                    	District dto = new District();
                        dto.setDistrictId(entity.getDistrictId());
                        dto.setDistrictName(entity.getDistrictName());
                        return dto;
                    })
                    .collect(Collectors.toList());
            outcome.setData(districtDropDownList);
            outcome.setOutcome(true);
            outcome.setMessage("Districts fetched successfully");
        } catch (Exception e) {
        	log.error("Exception occured in getAllActiveDistrictsIdAndNameByState() of MasterCommonServiceImpl" + e.getMessage());
        	e.printStackTrace();
            outcome.setData(List.of());
            outcome.setOutcome(false);
            outcome.setMessage("Failed to fetch districts: " + e.getMessage());
        }
        return outcome;
    }

	@Override
	public ServiceOutcome<List<Block>> getAllActiveBlockIdAndNameByDistrict(Long districtId) {
		ServiceOutcome<List<Block>> outcome = new ServiceOutcome<>();
		try {
			List<Block> blockDropDownList = blockRepository.findAllByDistrictDistrictIdAndIsActiveTrue(districtId);
			List<Block> BlockList = blockDropDownList.stream().map(block -> {
				Block dto = new Block();
				dto.setBlockId(block.getBlockId());
				dto.setBlockNameEN(block.getBlockNameEN());
				return dto;
			}).collect(Collectors.toList());

			outcome.setData(BlockList);
		} catch (Exception e) {
			outcome.setData(null);
			log.error("Exception occured in getAllActiveBlockIdAndNameByDistrict of MasterCommonServiceImpl" + e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	}
	
	@Override
	public ServiceOutcome<List<Municipality>> getAllActiveMunicipalityIdAndNameByDistrict(Long districtId) {
		ServiceOutcome<List<Municipality>> outcome = new ServiceOutcome<>();
		try {
			List<Municipality> municipalityDropDownList = municipalityRepository.findAllByDistrictDistrictIdAndIsActiveTrue(districtId);
			List<Municipality> municipalityDownDtoList = municipalityDropDownList.stream().map(municipality -> {
				Municipality dto = new Municipality();
				dto.setMunicipalityId(municipality.getMunicipalityId());
				dto.setMunicipalityName(municipality.getMunicipalityName());
				return dto;
			}).collect(Collectors.toList());

			outcome.setData(municipalityDownDtoList);
		} catch (Exception e) {
			outcome.setData(null);
			log.error("Exception occured in getAllActiveMunicipalityIdAndNameByDistrict of MasterCommonServiceImpl" + e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	}
	
	@Override
	public ServiceOutcome<List<Ward>> getAllActiveWardIdAndNameByMunicipalityId(Long municipalityId) {
		ServiceOutcome<List<Ward>> outcome = new ServiceOutcome<>();
		try {
			List<Ward> wardDropDownList = wardRepository.findAllByMunicipalityMunicipalityIdAndIsActiveTrue(municipalityId);
			List<Ward> WardList = wardDropDownList.stream().map(ward -> {
				Ward dto = new Ward();
				dto.setWardId(ward.getWardId());
				dto.setWardName(ward.getWardName());
				return dto;
			}).collect(Collectors.toList());

			outcome.setData(WardList);
		} catch (Exception e) {
			outcome.setData(null);
			log.error("Exception occured in getAllActiveWardIdAndNameByMunicipalityId of MasterCommonServiceImpl" + e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	}
	
	@Override
	public ServiceOutcome<List<Grampanchayat>> getAllActiveGrampanchayatIdAndNameByBlock(Long blockId) {
		ServiceOutcome<List<Grampanchayat>> outcome = new ServiceOutcome<>();
		try {
			List<Grampanchayat> GrampanchayatDropDownList = GrampanchayatRepository.findAllByBlockId(blockId, true);
			List<Grampanchayat> GrampanchayatList = GrampanchayatDropDownList.stream()
					.map(Grampanchayat -> {
						Grampanchayat dto = new Grampanchayat();
						dto.setGpId(Grampanchayat.getGpId());
						dto.setGpNameEN(Grampanchayat.getGpNameEN());
						return dto;
					}).collect(Collectors.toList());

			outcome.setData(GrampanchayatList);
		} catch (Exception e) {
			outcome.setData(null);
			log.error("Exception occured in getAllActiveGrampanchayatIdAndNameByBlock of MasterCommonServiceImpl" + e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	}

	@Override
	public ServiceOutcome<List<Village>> getAllActiveVillageIdAndNameByGrampanchayat(Long gpId) {
		ServiceOutcome<List<Village>> outcome = new ServiceOutcome<>();
		try {
			List<Village> villageDropDownList = villageRepository.findAllByGpIdGpIdAndIsActiveTrue(gpId);
			List<Village> VillageList = villageDropDownList.stream()
					.map(village -> {
						Village dto = new Village();
						dto.setVillageId(village.getVillageId());
						dto.setVillageNameEn(village.getVillageNameEn());
						return dto;
					}).collect(Collectors.toList());

			outcome.setData(VillageList);

		} catch (Exception e) {
			outcome.setData(null);
			log.error("Exception occured in getAllActiveVillageIdAndNameByGrampanchayat of MasterCommonServiceImpl" + e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	}

	@Override
	public ServiceOutcome<List<FinancialYear>> getAllActiveFinancialYear() {
		ServiceOutcome<List<FinancialYear>> outcome = new ServiceOutcome<>();
		try {
			List<FinancialYear> finYearList = financialYearRepository.findByIsActiveTrueOrderByFinYear();

			List<FinancialYear> finYearDropDownList = finYearList.stream().map(finYear -> {
				FinancialYear dto = new FinancialYear();
				dto.setFinyearId(finYear.getFinyearId());
				dto.setFinYear(finYear.getFinYear());
				return dto;
			}).collect(Collectors.toList());

			outcome.setData( finYearDropDownList);
		} catch (Exception e) {
			outcome.setData(null);
			log.error("Exception occured in getAllActiveFinancialYear() of MasterCommonServiceImpl" + e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	}
	
	@Override
	public ServiceOutcome<List<LookupValueDTO>> getLookupValuesByLookupCode(String lookupCode) {
        ServiceOutcome<List<LookupValueDTO>> outcome = new ServiceOutcome<>();
        try {
        	List<CommonLookupValue> entities = commonLookupValueRepository.findByCommonLookup_LookupCodeAndIsActive(lookupCode, true);
        	 
            if (entities == null || entities.isEmpty()) {
                outcome.setData(Collections.emptyList());
                outcome.setOutcome(false);
                outcome.setMessage("No data found for lookup code: " + lookupCode);
            } else {
                List<LookupValueDTO> dtos = lookupValueMapper.toDTOList(entities);
                outcome.setData(dtos);
                outcome.setOutcome(true);
                outcome.setMessage("Data Retrieved Successfully.");
            }
        } catch (Exception e) {
            log.error("Exception occurred in getLookupValuesByLookupCode() for lookupCode: {}", lookupCode, e);
            outcome.setData(Collections.emptyList());
            outcome.setOutcome(false);
            outcome.setMessage("Error retrieving data: " + e.getMessage());
        }
        return outcome;
    }

	@Override
	public String generateCode(String input, int length) {
		if (input == null || input.isEmpty()) {
			return "";
		}
		input = input.replaceAll("\\s+", ""); 
		return input.substring(0, Math.min(length, input.length())).toUpperCase();
	}
	
	//handle file upload 
	@Override
	public String handleFileUpload(MultipartFile file, String basePath, String folderName) {
		try {
			if (file == null || file.isEmpty()) {
				return null;
			}
			return UploadFile.uploadFileIntoDataBase(file, basePath, folderName,"DOC" + RandomNumberGenerate.getRandomNumber());
		} catch (IOException e) {
			log.error("Exception occured in handleFileUpload() of MasterCommonServiceImpl" , folderName, e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	

	
 }