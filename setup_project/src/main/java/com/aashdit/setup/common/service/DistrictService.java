package com.aashdit.setup.common.service;


import java.util.List;

import com.aashdit.setup.common.model.District;
import com.aashdit.setup.core.ServiceOutcome;

public interface DistrictService {

    ServiceOutcome<List<District>> getAllDistrict(Boolean includeDeleted);

    ServiceOutcome<District> getById(Long districtId);

    ServiceOutcome<List<District>> search(District searchParams);

    ServiceOutcome<District> saveDistrict(District district);

    ServiceOutcome<District> updateDistrict(District district);

    ServiceOutcome<District> deleteDistrict(Long districtId);
    
    ServiceOutcome<List<District>> listByStateId(Long stateId);

}
