package com.aashdit.prod.heads.hims.umt.repository;

import com.aashdit.prod.heads.hims.umt.model.MailQueued;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface MailQueuedRepository extends JpaRepository<MailQueued, Long>{

	@Query("FROM MailQueued WHERE status=:status")
	List<MailQueued> findEmailList(@Param("status") String status);

	List<MailQueued> findAllByStatus(String statuse);

	@Query("select m from MailQueued m where m.status = :status and m.mailPriority = :mailPriority order by m.entryId")
	List<MailQueued> findByStatusEqualsAndMailPriority(@Param("status") String status, @Param("mailPriority") String mailPriority);
}
