package com.aashdit.setup.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.setup.common.model.ItemOfWork;

public interface ItemOfWorkRepository extends JpaRepository<ItemOfWork, Long> {

	List<ItemOfWork> findAllByIsActive(Boolean action);

	List<ItemOfWork> findAllByIsActiveTrue();

	ItemOfWork findByIdAndIsActiveTrue(Long id);
	
	 @Query(value = "SELECT iow.* " +
             "FROM public.item_of_work iow " +
             "WHERE iow.work_item_name = :name " +
             "AND iow.is_active = true", nativeQuery = true)
	 List<ItemOfWork> validateNameAndIsActiveTrue(@Param("name") String name);

}
