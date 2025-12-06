package com.aashdit.setup.service;

import com.aashdit.setup.umt.model.User;

public interface MailService {

	Boolean mailPasswordDetails(String password, String subject, User user);

	Boolean mailLoginCredentials(String password, String subject, User user);

	Boolean sendOtp(String otp, String emailId);

}
