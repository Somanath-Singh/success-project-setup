package com.aashdit.prod.heads.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.prod.heads.common.model.AppConfigParameters;

public interface AppConfigParametersRepository extends JpaRepository<AppConfigParameters, Long> {

	AppConfigParameters findByParamCode(String taxParamCode);

}
