package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.prod.heads.common.model.AdditionalCostMaster;

public interface AdditionalCostMasterRepository extends JpaRepository<AdditionalCostMaster,Long> {
    List<AdditionalCostMaster> findAllByIsActiveIsTrue();
}
