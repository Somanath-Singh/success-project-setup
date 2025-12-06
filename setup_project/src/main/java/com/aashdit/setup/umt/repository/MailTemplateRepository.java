package com.aashdit.setup.umt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.umt.model.MailTemplatesMaster;

import java.util.Optional;


@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplatesMaster, Long> {

	MailTemplatesMaster findByTemplateTypeAndTemplateCode(String templateType, String templateCode);

	@Query("select m from MailTemplatesMaster m where m.templateCode = :templateCode")
	Optional<MailTemplatesMaster> findByTemplateCode(@Param("templateCode") String templateCode);
}
