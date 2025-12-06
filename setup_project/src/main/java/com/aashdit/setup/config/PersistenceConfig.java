package com.aashdit.setup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import com.aashdit.setup.dto.UserVO;

@Component
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig {

	@Bean
	AuditorAware<UserVO> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
