package com.aashdit.prod.heads.hims.ipms.service;

import com.aashdit.prod.heads.framework.core.ServiceOutcome;
import com.aashdit.prod.heads.hims.ipms.dto.*;
import com.aashdit.prod.heads.hims.ipms.model.*;


public interface RegistrationFormService {

	ServiceOutcome<String> saveRegistrationDetails(RegistrationFormDto registrationFormDto);

	ServiceOutcome<RegistrationForm> getRegistrationDetailsObjectId(Long objectId);

	ServiceOutcome<RegistrationFormDto> getByregistrationId(Long registrationId);

	ServiceOutcome<RegistrationForm> processStatus(RegistrationFormDto statusDto);

	ServiceOutcome<RegistrationFormDto> getAllRegistration();

}
