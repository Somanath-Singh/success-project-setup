package com.aashdit.setup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.aashdit.setup.umt.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtFilter jwtFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncode());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().contentTypeOptions().and().xssProtection().and().cacheControl().and()
				.httpStrictTransportSecurity().and().frameOptions().and()
				.contentSecurityPolicy("script-src 'self' 'unsafe-eval' 'unsafe-inline'").and()
				.referrerPolicy(ReferrerPolicy.SAME_ORIGIN);

		http.authorizeRequests().antMatchers(HttpMethod.TRACE, "/**").denyAll().antMatchers(HttpMethod.PATCH, "/**")
				.denyAll().antMatchers(HttpMethod.PUT, "/**").denyAll().antMatchers(HttpMethod.DELETE, "/**").denyAll()
				.antMatchers(HttpMethod.HEAD, "/**").denyAll().antMatchers(HttpMethod.OPTIONS, "/**").denyAll()
				.antMatchers("/captcha/**").permitAll().antMatchers("/umt/login").permitAll().antMatchers("/assets/**")
				.permitAll().antMatchers("/overwrite/**").permitAll().antMatchers("/privacyPolicy").permitAll()
				.antMatchers("/system/**").permitAll().antMatchers("/api/**").permitAll()
				.antMatchers("/mPin/check-pin/**").permitAll().antMatchers("/mPin/generate-update-pin/**").permitAll()
				.antMatchers("/mPin/send-otp/**").permitAll().antMatchers("/mPin/verify-otp-reset-pin/**").permitAll()
				.antMatchers("/public/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/login").invalidateHttpSession(true)
				.deleteCookies("Invalid Session", "JSESSIONID").permitAll();

		http.cors().and().csrf().ignoringAntMatchers("/ajax/**", "/public/**", "/core/**", "/dashboard/**", "/api/**",
				"/api/login", "/api/user-roles", "/mPin/check-pin/**", "/mPin/generate-update-pin/**",
				"/mPin/send-otp/**", "/mPin/verify-otp-reset-pin/**");
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository() {
		return new HttpSessionCsrfTokenRepository();
	}

}
