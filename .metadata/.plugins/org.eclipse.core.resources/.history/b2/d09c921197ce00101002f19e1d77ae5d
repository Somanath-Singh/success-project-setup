package com.aashdit.prod.heads.hims.ipms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.hims.ipms.model.WebsiteContentType;


@Repository
public interface WebsiteContentTypeRepository extends JpaRepository<WebsiteContentType, Long> {

	WebsiteContentType findByContentTypeId(Long contentId);

	List<WebsiteContentType> findAllByIsActiveTrue();

}
