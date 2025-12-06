package com.aashdit.setup.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.setup.common.model.AssetVendor;

public interface AssetVendorRepository extends JpaRepository<AssetVendor, Long>{

	List<AssetVendor> findAllByIsActiveIsTrue();

	@Query("select v from AssetVendor v order by v.vendorName")
	List<AssetVendor> findByOrderByVendorNameAsc();

	@Query("select a from AssetVendor a " +
			"where a.entityId = :entityId and a.entityLevel = :entityLevel " +
			"order by a.vendorName")
	List<AssetVendor> findByEntityIdAndEntityLevelNameAsc(@Param("entityId") Long entityId, @Param("entityLevel") String entityLevel);

}
