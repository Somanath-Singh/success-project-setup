package com.aashdit.setup.umt.service;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.core.util.RandomString;
import com.aashdit.setup.umt.model.User;
import com.aashdit.setup.umt.repository.UserRepository;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService, MessageSourceAware {
	final static Logger logger = Logger.getLogger(ForgotPasswordServiceImpl.class);

	private MessageSource messageSource;

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UmtMailService mailService;

	@Transactional
	@Override
	public ServiceOutcome<Boolean> forgotPassword(String username) {
		ServiceOutcome<Boolean> svcOutcome = new ServiceOutcome<Boolean>();
		String alertCheck = "msg.error";
		Boolean dataCheck = false;
		try {
			User user = userRepository.findByUserName(username);
			if (Optional.ofNullable(user).isPresent()) {
				RandomString rd = new RandomString(10);
				@SuppressWarnings("unused")
				String password = rd.nextString();
				user.setPassword("$2a$11$7lmqnEYaww74clWFqyi4k.mUech7cgSE8nYPkut1EszzcuC3i7k.K");
				user = userRepository.save(user);
				String message = mailService.getMessage("FORGET_PASSWORD", "ALL_USER") + " " + "123456";
				Boolean mailsaved = mailService.mailPasswordDetails(message, "FORGOT PASSWORD", user);
				if (mailsaved) {
					alertCheck = messageSource.getMessage("site.common.passwordchange", null,
							LocaleContextHolder.getLocale());
					dataCheck = true;
				} else {
					alertCheck = messageSource.getMessage("site.common.passwordFailed", null,
							LocaleContextHolder.getLocale());
					dataCheck = true;
				}
			} else {
				alertCheck = messageSource.getMessage("msg.userInvalid", null, LocaleContextHolder.getLocale());
				dataCheck = false;
			}
			svcOutcome.setData(dataCheck);
			svcOutcome.setOutcome(true);
			svcOutcome.setMessage(alertCheck);
		} catch (Exception e) {
			logger.error("Exception occured in forgotPassword method in ForgotPasswordServiceImpl-->" + e.getMessage());
			svcOutcome.setData(dataCheck);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(alertCheck);
		}
		return svcOutcome;
	}

}
