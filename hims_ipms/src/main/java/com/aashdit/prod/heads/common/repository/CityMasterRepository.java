package com.aashdit.prod.heads.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.common.model.CityMaster;

@Repository
public interface CityMasterRepository extends JpaRepository<CityMaster, Long> {

}
