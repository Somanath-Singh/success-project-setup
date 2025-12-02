package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.common.model.CommonLookupValue;

@Repository
public interface CommonLookupValueRepository extends JpaRepository<CommonLookupValue,Long >{
	
	List<CommonLookupValue> findByCommonLookup_LookupCodeAndIsActive(String lookupCode, boolean isActive);

	 List<CommonLookupValue> findByLookupCode(String lookupCode);
}
