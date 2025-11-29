package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.prod.heads.common.model.Month;

public interface MonthRepository extends JpaRepository<Month, Long> {
    Month findByMonthCode(String upperCase);

    List<Month> findAllByOrderByDisplayOrderAsc();
    
    List<Month> findAllByOrderByMonthId() ;
}