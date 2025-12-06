package com.aashdit.setup.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aashdit.setup.dto.UserVO;
import com.aashdit.setup.umt.dto.CurrentUserVo;
import com.aashdit.setup.umt.model.LoggedInUser;
import com.aashdit.setup.umt.model.User;

public class SecurityHelper {
	
	public static User getCurrentUser() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LoggedInUser currentUser = (LoggedInUser) auth.getPrincipal();
			CurrentUserVo currUserVo = currentUser.getCurrentUserVo();
			User user = currentUser.getDbUser();
			user.setCurrentUserVo(currUserVo);
			return user;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public static UserVO getCurrentUserVO() {
		try {
			UserVO user = (UserVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return user;
		}
		catch(Exception e) {
			return null;
		}
	}

}
