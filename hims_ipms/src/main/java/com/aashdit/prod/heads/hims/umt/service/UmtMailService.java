package com.aashdit.prod.heads.hims.umt.service;

import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.specs.MailPriority;

public interface UmtMailService {

	String getMessage(String string, String string2);
	
	Boolean mailPasswordDetails(String message, String subject, User user);
	
	Boolean mailRegistrationDetails(String templateCode, String mail, MailPriority priority, String... emailValues);
	
}
