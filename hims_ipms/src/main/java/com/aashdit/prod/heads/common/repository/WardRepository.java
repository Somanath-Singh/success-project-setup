package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.prod.heads.common.model.Ward;

public interface WardRepository extends JpaRepository<Ward, Long> {

	Ward findByWardId(Long wardId);

	List<Ward> findAllByMunicipalityMunicipalityId(Long municipalityId);
	
	List<Ward> findAllByMunicipalityMunicipalityIdAndIsActiveTrue(Long municipalityId);

}
