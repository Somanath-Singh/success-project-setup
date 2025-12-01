package com.aashdit.prod.heads.hims.ipms.config;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.regex.Pattern;

/** @Author= @Soumya k khatua **/	
public class SanitizationInterceptor implements HandlerInterceptor {
	   private static final Logger logger = LoggerFactory.getLogger(SanitizationInterceptor.class);

	    // Compile the regex patterns once
	    private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
	    private static final Pattern FORMAT_SPECIFIER_PATTERN = Pattern.compile("%[a-zA-Z]", Pattern.CASE_INSENSITIVE); // Matches format specifiers

	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
	        // Check parameters, headers, cookies, and URL path for malicious content
	        boolean hasInvalidInput = hasInvalidInput(request.getParameterMap()) ||
	                                  hasInvalidInput(request.getHeaderNames(), request) ||
	                                  hasInvalidInput(request.getCookies()) ||
	                                  hasInvalidInput(request.getRequestURI());

	        if (hasInvalidInput) {
	            String requestDetails = String.format("Request URI: %s, Parameters: %s, Headers: %s, Cookies: %s",
	                request.getRequestURI(),
	                request.getParameterMap(),
	                Collections.list(request.getHeaderNames()),
	                Arrays.toString(request.getCookies()));
	            logger.warn("Malicious content detected. Details: {}", requestDetails);
	            response.sendRedirect(request.getContextPath() + "/site-400"); // Redirect to custom error page
	            return false;
	        }

	        return true;
	    }

	    private boolean hasInvalidInput(Map<String, String[]> parameterMap) {
	        return parameterMap.values().stream()
	            .flatMap(Arrays::stream)
	            .anyMatch(this::containsInvalidContent);
	    }

	    private boolean hasInvalidInput(Enumeration<String> headerNames, HttpServletRequest request) {
	        while (headerNames.hasMoreElements()) {
	            String headerName = headerNames.nextElement();
	            String headerValue = request.getHeader(headerName);
	            if (containsInvalidContent(headerValue)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    private boolean hasInvalidInput(Cookie[] cookies) {
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (containsInvalidContent(cookie.getValue())) {
	                    return true;
	                }
	            }
	        }
	        return false;
	    }

	    private boolean hasInvalidInput(String input) {
	        return containsInvalidContent(input);
	    }

	    private boolean containsInvalidContent(String input) {
	        return input != null && (containsHtmlTags(input) || containsFormatSpecifiers(input));
	    }

	    private boolean containsHtmlTags(String input) {
	        return HTML_TAG_PATTERN.matcher(input).find();
	    }

	    private boolean containsFormatSpecifiers(String input) {
	        return FORMAT_SPECIFIER_PATTERN.matcher(input).find();
	    }
}

