package com.aashdit.setup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.aashdit.*"})
@EnableJpaRepositories(basePackages = {"com.aashdit.*"})
@EntityScan(basePackages = {"com.aashdit.*"})
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class SetupProjectApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SetupProjectApplication.class, args);
	}

}

