package com.aashdit.setup.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.aashdit.setup.security.XSSFilter;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.aashdit.*")
public class WebMvcConfigureAdapter implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/")
				.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

		registry.addResourceHandler("/loginPage/**").addResourceLocations("/loginPage/")
				.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
	}

	@Bean
	public FilterRegistrationBean<XSSFilter> xssPreventFilter() {
		FilterRegistrationBean<XSSFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new XSSFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("layout.login");
	}

	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
		messageResource.setBasename("classpath:i18n/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}

	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		CookieLocaleResolver r = new CookieLocaleResolver();
		r.setDefaultLocale(Locale.ENGLISH);
		r.setCookieName("localeInfo");

		/** if set to -1, the cookie is deleted when browser shuts down */
		r.setCookieMaxAge(24 * 60 * 60);
		return r;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
	}

}
