package com.aashdit.setup.umt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aashdit.setup.umt.model.ActivityLevelMap;

import java.util.Optional;

public interface ActivityLevelMapRepository extends JpaRepository<ActivityLevelMap, Long> {

    @Query("select a from ActivityLevelMap a where a.tableModuleName = ?1 and a.isActive = true")
    Optional<ActivityLevelMap> findByTableModuleName(String tableModuleName);
    
}