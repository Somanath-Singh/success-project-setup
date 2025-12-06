package com.aashdit.setup.umt.service;


import com.aashdit.setup.core.ServiceOutcome;

public interface ForgotPasswordService {

	ServiceOutcome<Boolean> forgotPassword(String username);

}
