package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.prod.heads.common.model.VendorType;

public interface VendorTypeRepository extends JpaRepository<VendorType, Long>{

	List<VendorType> findAllByIsActiveIsTrue();

	VendorType findByVendorTypeNameAndIsActiveIsTrue(String string);

}
