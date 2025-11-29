package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aashdit.prod.heads.common.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findAllByIsActiveIsTrue();

	@Query("select c from Category c order by c.categoryName")
	List<Category> findAllByOrderByCategoryNameAsc();

	Category findByCategoryName(String catName);

	Category findByCategoryCode(String categoryCode);
}
