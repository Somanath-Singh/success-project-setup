package com.aashdit.setup.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.setup.model.ApplicationModuleMst;

public interface ApplicationModuleMstRepository extends JpaRepository<ApplicationModuleMst, Long> {

    // get module by module code in operator
    @Query("FROM ApplicationModuleMst where moduleCode in :moduleCodes and isActive = true order by displayOrder")
    List<ApplicationModuleMst> findByModuleCodes(@Param("moduleCodes") Set<String> moduleCodes);

    @Query("FROM ApplicationModuleMst where moduleCode = :moduleCode and isActive = true order by displayOrder")
    Optional<ApplicationModuleMst> findByModuleCode(@Param("moduleCode") String moduleCode);

	List<ApplicationModuleMst> findAllByIsActiveTrue();

}