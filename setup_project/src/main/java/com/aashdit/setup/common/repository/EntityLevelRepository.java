package com.aashdit.setup.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.common.model.EntityLevel;


@Repository
public interface EntityLevelRepository extends JpaRepository<EntityLevel, Long> {

	EntityLevel findByValueCode(String entityLvl);
	List<EntityLevel> findByCode(String entityLvl);


}
