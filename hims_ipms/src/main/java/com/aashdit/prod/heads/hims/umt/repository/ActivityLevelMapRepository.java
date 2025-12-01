package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.ActivityLevelMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ActivityLevelMapRepository extends JpaRepository<ActivityLevelMap, Long> {

    @Query("select a from ActivityLevelMap a where a.tableModuleName = ?1 and a.isActive = true")
    Optional<ActivityLevelMap> findByTableModuleName(String tableModuleName);
    
}