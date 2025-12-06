package com.aashdit.setup.common.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.common.model.Year;

@Repository
public interface YearRepository extends JpaRepository<Year, Long> {

	List<Year> findByIsActiveTrueOrderByOrderIdAsc();

	List<Year> findAllByOrderByOrderIdDesc();

//	List<Year> findAllByOrderByOrderIdDesc();


}
