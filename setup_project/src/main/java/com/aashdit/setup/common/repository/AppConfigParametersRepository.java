package com.aashdit.setup.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.setup.common.model.AppConfigParameters;

public interface AppConfigParametersRepository extends JpaRepository<AppConfigParameters, Long> {

	AppConfigParameters findByParamCode(String taxParamCode);

}
