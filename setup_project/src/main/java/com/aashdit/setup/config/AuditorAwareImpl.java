package com.aashdit.setup.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.aashdit.setup.dto.UserVO;
import com.aashdit.setup.utils.SecurityHelper;

public class AuditorAwareImpl implements AuditorAware<UserVO> {

	@Override
	public Optional<UserVO> getCurrentAuditor() {
		return Optional.ofNullable(SecurityHelper.getCurrentUserVO());
	}

}
