package com.aashdit.setup.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.setup.common.model.District;

public interface DistrictRepository extends JpaRepository<District, Long> {
	@Query("select d from District d where d.isActive = :isActive order by d.districtName")
	List<District> findByIsActiveEquals(@Param("isActive") Boolean isActive);
	
	
	List<District> findAllByIsActiveTrueOrderByDistrictName();
	
	District findByDistrictId(Long districtId);
	
	List<District> findAllByStateIdStateIdAndIsActiveTrue(Long demoId);

	@Query("FROM District where isActive = :isActive and stateId.stateId=:stateId")
	List<District> findAllByStateId(@Param("stateId") Long stateId, @Param("isActive") Boolean isActive);

	List<District> findAllByIsActiveTrue();

	@Query("select d from District d where d.districtCode = :districtCode")
	District findByDistrictCode(@Param("districtCode") String districtCode);

	@Query("select d from District d where d.districtName = :districtName")
	District checkUniqueDistrictNameEn(@Param("districtName") String districtName);


	List<District> findAllByIsActiveOrderByDistrictNameAsc(Boolean isActive);
}

