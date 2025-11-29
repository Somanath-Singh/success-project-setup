package com.aashdit.prod.heads.hims.ipms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.hims.ipms.model.WebsiteSliderImage;

@Repository
public interface WebsiteSliderImageRepository extends JpaRepository<WebsiteSliderImage, Long> {

	List<WebsiteSliderImage> findAllByIsActiveTrue();

	WebsiteSliderImage findByWebSliderId(Long webSliderId);

}
