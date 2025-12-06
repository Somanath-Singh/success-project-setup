package com.aashdit.setup.umt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.umt.model.RoleRightLevelMaster;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRightLevelMasterRepository extends JpaRepository<RoleRightLevelMaster, Long> {

	RoleRightLevelMaster findByLevelCode(String levelCode);
	
	List<RoleRightLevelMaster> findByIsActive(Boolean isActive);

	@Query("select r from RoleRightLevelMaster r where r.levelEntityName = :levelEntityName")
	Optional<RoleRightLevelMaster> findByLevelEntityName(@Param("levelEntityName") String levelEntityName);

	@Query("select r from RoleRightLevelMaster r where r.levelKeyClass = :levelKeyClass")
	Optional<RoleRightLevelMaster> findByLevelKeyClass(@Param("levelKeyClass") String levelKeyClass);


	@Query("select r from RoleRightLevelMaster r " +
			"where r.isActive = true and r.displayOnUi = :displayOnUi " +
			"order by r.levelCode")
	List<RoleRightLevelMaster> getOrganizationLevelListByDisplayOnUI(@Param("displayOnUi") Boolean displayOnUi);

	@Query("select r from RoleRightLevelMaster r " +
			"where r.isActive = true and r.displayAtConfig = :displayAtConfig " +
			"order by r.levelCode")
	List<RoleRightLevelMaster> getOrganizationLevelListByDisplayAtConfig(@Param("displayAtConfig") Boolean displayAtConfig);
}
