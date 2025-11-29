package com.aashdit.prod.heads.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.common.model.GoverningBody;

@Repository
public interface GoverningBodyRepository extends JpaRepository<GoverningBody, Long> {

	List<GoverningBody> findAllByIsActive(Boolean isActive);

	List<GoverningBody> findAllByIsActiveTrue();

	@Query("select g from GoverningBody g where g.emailId = ?1")
	Optional<GoverningBody> findByEmailId(String emailId);
}
