package com.aashdit.prod.heads.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.common.model.CodeGenerationConfig;

@Repository
public interface CodeGenerationConfigRepository extends JpaRepository<CodeGenerationConfig, Long> {
	
	@Query(value = "select\r\n"
			+ "	cgc.*\r\n"
			+ "from\r\n"
			+ "	public.code_generation_config cgc\r\n"
			+ "where\r\n"
			+ "	cgc.is_active = true\r\n"
			+ "	and cgc.module_type = :moduleType", nativeQuery = true)
	CodeGenerationConfig getCodeGenerationConfigByModuleName(@Param("moduleType") String moduleType);

}