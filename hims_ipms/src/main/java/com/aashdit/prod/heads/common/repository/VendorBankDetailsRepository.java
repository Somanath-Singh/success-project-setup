package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.prod.heads.common.model.VendorBankDetails;

public interface VendorBankDetailsRepository extends JpaRepository<VendorBankDetails,Long> {
    List<VendorBankDetails> findAllByVendorIdVendorId(Long vendorId);
}
