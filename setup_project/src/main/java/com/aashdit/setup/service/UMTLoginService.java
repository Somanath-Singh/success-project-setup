package com.aashdit.setup.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.model.User;


public interface UMTLoginService {

	ServiceOutcome<Boolean> createFailedLoginHistory(User user, HttpServletRequest request, HttpServletResponse httpServletResponse);

	ServiceOutcome<Boolean> createNoUserLoginHistory(String userName, HttpServletRequest request, HttpServletResponse httpServletResponse);

	ServiceOutcome<String> sendOtpByUserName(String userName, HttpSession session);

	String resetOTP(String userName, HttpSession httpSession);

	ServiceOutcome<String> resetPassword(Long userId);

	ServiceOutcome<String> sendOtp(String userName, HttpSession session, String emailId);

	String resetOTPByMob(String userName, HttpSession httpSession);



}
