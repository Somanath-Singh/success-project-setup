package com.aashdit.prod.heads.hims.ipms.service;

import com.aashdit.prod.heads.hims.umt.model.User;

public interface MailService {

	Boolean mailPasswordDetails(String password, String subject, User user);

	Boolean mailLoginCredentials(String password, String subject, User user);

	Boolean sendOtp(String otp, String emailId);

}
