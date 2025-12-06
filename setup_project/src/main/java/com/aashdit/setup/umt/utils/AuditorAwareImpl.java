package com.aashdit.setup.umt.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aashdit.setup.umt.model.LoggedInUser;
import com.aashdit.setup.umt.model.User;

public class AuditorAwareImpl implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			return Optional.empty();
		}
		LoggedInUser user = (LoggedInUser) auth.getPrincipal();
		if (user != null) {
			User dbUser = user.getDbUser();
			return Optional.of(dbUser.getUserId());
		}
		return Optional.empty();
	}

}
