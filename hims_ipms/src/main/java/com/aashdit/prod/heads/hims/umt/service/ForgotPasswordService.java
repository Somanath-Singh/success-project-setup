package com.aashdit.prod.heads.hims.umt.service;


import com.aashdit.prod.heads.framework.core.ServiceOutcome;

public interface ForgotPasswordService {

	ServiceOutcome<Boolean> forgotPassword(String username);

}
