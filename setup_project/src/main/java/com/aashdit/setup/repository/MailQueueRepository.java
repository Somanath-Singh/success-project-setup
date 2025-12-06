package com.aashdit.setup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.model.MailQueue;



@Repository
public interface MailQueueRepository extends JpaRepository<MailQueue, Long> {

	List<MailQueue> findByStatus(String status);

}