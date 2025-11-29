package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.prod.heads.common.model.Municipality;

public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {

	List<Municipality> findAllByDistrictDistrictId(Long districtId);
	
	List<Municipality> findAllByDistrictDistrictIdAndIsActiveTrue(Long districtId);

	Municipality findByMunicipalityId(Long municipalityId);

	List<Municipality> findAllByIsActiveTrueOrderByMunicipalityName();

	List<Municipality> findAllByMunicipalityIdAndIsActiveTrue(Long userLevelId);

	List<Municipality> findAllByIsActive(Boolean isActive);

	List<Municipality> findAllByIsActiveTrue();

	List<Municipality> findAllByGoverningBodyIdGoverningBodyId(Long govId);

	List<Municipality> findAllByGoverningBodyIdGoverningBodyIdAndIsActive(Long govId, Boolean isActive);

	@Query("select m from Municipality m " +
			"where m.parentObjectId = :parentObjectId and m.parentObjectType = :parentObjectType " +
			"and m.isActive = true")
	List<Municipality> findByParent(@Param("parentObjectId") Long parentObjectId, @Param("parentObjectType") String parentObjectType);

	@Query("select m from Municipality m " +
			"where m.parentObjectId = :parentObjectId and m.parentObjectType = :parentObjectType ")
	List<Municipality> findByParentAll(@Param("parentObjectId") Long parentObjectId, @Param("parentObjectType") String parentObjectType);

	@Query("select m from Municipality m where m.governingBodyId is null order by m.municipalityName")
	List<Municipality> findByGoverningBodyIdIsNull();
}
