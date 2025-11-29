package com.aashdit.prod.heads.hims.ipms.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aashdit.prod.heads.hims.ipms.controller.CustomLoginController;
import com.aashdit.prod.heads.hims.umt.model.LoggedInUser;
import com.aashdit.prod.heads.hims.umt.model.Role;
import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.model.UserRoleMap;
import com.aashdit.prod.heads.hims.umt.service.RoleService;
import com.aashdit.prod.heads.hims.umt.service.UserService;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CustomLoginController customLoginController;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		if (!httpServletRequest.getMethod().equals("OPTIONS")) {
			if (httpServletRequest.getRequestURI().contains("/api/login")
					|| httpServletRequest.getRequestURI().contains("/api/save-public")
					|| httpServletRequest.getRequestURI().contains("/public/")) {
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			} else {
				if (httpServletRequest.getRequestURI().contains("/api")) {
					if (httpServletRequest.getRequestURI().contains("/api/allow")) {
						filterChain.doFilter(httpServletRequest, httpServletResponse);
						return;
					}
					String authorizationHeader = httpServletRequest.getHeader("Authorization");
					String token = null;
					String userName = null;
					if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
						token = authorizationHeader.substring(7);
						try {
							userName = jwtUtil.extractUsername(token);
							if (userName != null) {
								User user = userService.findByUsername(userName).getData();
								if (user != null) {
									if (jwtUtil.validateToken(token, user)) {

										LoggedInUser userDetails = getLoggedInUser(user);

										customLoginController.loadActiveUserRoleLevel(httpServletRequest, userDetails);

										filterChain.doFilter(httpServletRequest, httpServletResponse);
									} else {
										// throw new Exception("Session got expired.");
										httpServletResponse.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT,
												"Session got expired.");
									}
								} else {
									// throw new Exception("Session got expired.");
									httpServletResponse.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT,
											"Session got expired.");
								}
							} else {
								// throw new Exception("Session got expired.");
								httpServletResponse.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT, "Session got expired.");
							}
						} catch (Exception ex) {
							if (!httpServletResponse.isCommitted()) {
								httpServletResponse.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT,
										"Session got expired.");
							} else {
								httpServletResponse.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT,
										"Session got expired.");
							}
							filterChain.doFilter(httpServletRequest, httpServletResponse);
						}
					} else {
						httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
					}
				} else {
					filterChain.doFilter(httpServletRequest, httpServletResponse);
				}
			}
		} else {
			httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	private LoggedInUser getLoggedInUser(User user) {

		List<UserRoleMap> userRoleMaps = userService.findUserRoleMapByUserId(user.getUserId());
		userRoleMaps = userRoleMaps.stream().filter(r -> r.getIsActive().booleanValue()).collect(Collectors.toList());
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		List<Role> lstRoles = new ArrayList<>();
		for (UserRoleMap urm : userRoleMaps) {
			Role role = this.roleService.findByRoleId(urm.getRoleId()).getData();
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
			if (role.getIsActive()) lstRoles.add(role);
		}
		user.setRoles(lstRoles);
		LoggedInUser userDetails = new LoggedInUser(user.getUserName(), user.getPassword(), true, true, true, true, grantedAuthorities, user.getPrimaryRole(), user);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, grantedAuthorities);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(usernamePasswordAuthenticationToken);
		return userDetails;
	}

}