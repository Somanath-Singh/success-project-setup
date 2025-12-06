package com.aashdit.setup.umt.service;

import com.aashdit.setup.umt.model.User;
import com.aashdit.setup.umt.specs.MailPriority;

public interface UmtMailService {

	String getMessage(String string, String string2);
	
	Boolean mailPasswordDetails(String message, String subject, User user);
	
	Boolean mailRegistrationDetails(String templateCode, String mail, MailPriority priority, String... emailValues);
	
}
