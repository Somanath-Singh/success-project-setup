package com.aashdit.prod.heads.hims.ipms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.hims.ipms.model.MailQueue;



@Repository
public interface MailQueueRepository extends JpaRepository<MailQueue, Long> {

	List<MailQueue> findByStatus(String status);

}