package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.prod.heads.common.model.AdditionalCost;

public interface AdditionalCostRepository extends JpaRepository<AdditionalCost, Long>{

	List<AdditionalCost> findAllByAddCostTypeAndAddCostTypeIdAndIsActiveIsTrue(String string, Long rfqQuoteId);

	List<AdditionalCost> findAllByIsActiveIsTrue();
}
