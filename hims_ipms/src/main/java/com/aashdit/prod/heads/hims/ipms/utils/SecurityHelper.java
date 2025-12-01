package com.aashdit.prod.heads.hims.ipms.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aashdit.prod.heads.hims.ipms.dto.UserVO;

public class SecurityHelper {
	public static UserVO getCurrentUser() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			// User user =
			// (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			LoggedInUser currentUser = (LoggedInUser) auth.getPrincipal();
			return currentUser.getDbUser();
		} catch (Exception e) {
			return null;
		}
	}
}
