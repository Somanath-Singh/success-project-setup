package com.aashdit.prod.heads.hims.ipms.security;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/***
 * @Author Pramodini Jena
 * @Date 26/10/2018
 * @Modified Date 26/11/2018
 **/
@Slf4j
public class CrossScriptingFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void destroy() {}
	
	@Override

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
	}
}
