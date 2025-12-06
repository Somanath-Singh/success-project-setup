package com.aashdit.setup.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aashdit.setup.common.dto.LookupValueDTO;
import com.aashdit.setup.common.model.Block;
import com.aashdit.setup.common.model.District;
import com.aashdit.setup.common.model.FinancialYear;
import com.aashdit.setup.common.model.Grampanchayat;
import com.aashdit.setup.common.model.Municipality;
import com.aashdit.setup.common.model.State;
import com.aashdit.setup.common.model.Village;
import com.aashdit.setup.common.model.Ward;
import com.aashdit.setup.core.ServiceOutcome;

public interface MasterCommonService {
	
    ServiceOutcome<List<State>> getAllActiveStatesIdAndName();
    
    ServiceOutcome<List<District>> getAllActiveDistrictIdAndName();
    
    ServiceOutcome<List<District>> getAllActiveDistrictsIdAndNameByState(Long stateId);
	
	ServiceOutcome<List<Block>> getAllActiveBlockIdAndNameByDistrict(Long districtId);
	
	ServiceOutcome<List<Grampanchayat>> getAllActiveGrampanchayatIdAndNameByBlock(Long blockId);
	
	ServiceOutcome<List<Village>> getAllActiveVillageIdAndNameByGrampanchayat(Long gpId);
	
	ServiceOutcome<List<Municipality>> getAllActiveMunicipalityIdAndNameByDistrict(Long districtId);
	
	ServiceOutcome<List<Ward>> getAllActiveWardIdAndNameByMunicipalityId(Long municipalityId);
	
	ServiceOutcome<List<FinancialYear>> getAllActiveFinancialYear();
	
	ServiceOutcome<List<LookupValueDTO>> getLookupValuesByLookupCode(String lookupCode);
	
    String generateCode(String input, int length);
    
    String handleFileUpload(MultipartFile file, String basePath, String folderName);
    
}
