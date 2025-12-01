package com.aashdit.prod.heads.hims.ipms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.hims.ipms.model.WebMenu;


@Repository
public interface WebMenuRepository extends JpaRepository<WebMenu, Long> {

	List<WebMenu> findAllByIsActiveTrue();

	WebMenu findByMenuId(Long menuId);
}
