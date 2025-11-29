package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.UmtNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UmtNativeQueryRepository extends JpaRepository<UmtNativeQuery, Long> {
    @Query("select u from UmtNativeQuery u where u.queryCode = ?1 and u.isActive = true")
    Optional<UmtNativeQuery> findByQueryCode(String queryCode);
}