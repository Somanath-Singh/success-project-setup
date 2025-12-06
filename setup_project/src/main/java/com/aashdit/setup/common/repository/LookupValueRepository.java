package com.aashdit.setup.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.common.model.LookupValue;

@Repository
public interface LookupValueRepository extends JpaRepository<LookupValue, Long> {

	List<LookupValue> findByCode(String dcode);

}
