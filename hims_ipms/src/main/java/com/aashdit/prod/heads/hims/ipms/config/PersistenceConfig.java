package com.aashdit.prod.heads.hims.ipms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import com.aashdit.prod.heads.hims.ipms.dto.UserVO;

@Component
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig {

	@Bean
	AuditorAware<UserVO> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
