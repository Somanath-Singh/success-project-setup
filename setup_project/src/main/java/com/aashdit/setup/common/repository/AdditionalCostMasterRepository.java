package com.aashdit.setup.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.setup.common.model.AdditionalCostMaster;

public interface AdditionalCostMasterRepository extends JpaRepository<AdditionalCostMaster,Long> {
    List<AdditionalCostMaster> findAllByIsActiveIsTrue();
}
