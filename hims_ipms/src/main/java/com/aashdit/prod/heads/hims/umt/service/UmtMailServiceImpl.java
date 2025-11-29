package com.aashdit.prod.heads.hims.umt.service;


import java.io.StringWriter;
import java.util.Optional;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashdit.prod.heads.hims.umt.model.MailQueued;
import com.aashdit.prod.heads.hims.umt.model.MailTemplatesMaster;
import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.repository.MailQueuedRepository;
import com.aashdit.prod.heads.hims.umt.repository.MailTemplateRepository;
import com.aashdit.prod.heads.hims.umt.specs.MailPriority;


@Service
@Component
public class UmtMailServiceImpl implements UmtMailService {

	private static final Logger logger = Logger.getLogger(UmtMailServiceImpl.class);
	
	@Autowired
	private MailQueuedRepository mailQueuedRepository;
	
	@Autowired
	private MailTemplateRepository mailTemplateRepository;

	
	@Value("${mail.username}")
	private String mailUsername;
	

	@Override
	public String getMessage(String templateType, String templateCode) {
		String message="";
		try {
			MailTemplatesMaster geTemplate = mailTemplateRepository.findByTemplateTypeAndTemplateCode(templateType, templateCode);
			if(Optional.ofNullable(geTemplate).isPresent()) {
				message = geTemplate.getTemplateName();
			}
			
		} catch (Exception e) {
			logger.error("ERROR - MailServiceImpl -> generateInvitationMail : " + e.getMessage());
		}
		return message;
	}
	
	@Override
	@Transactional
	public Boolean mailPasswordDetails(String message, String subject, User user) {
		try {
//			String emailId = rb.getString("mail.username");
			Template template = new VelocityEngine().getTemplate("templates/mailTemplate.vm");

			
			VelocityContext velocityContext = new VelocityContext();
//			velocityContext.put("userName", user.getFirstName()+" "+user.getLastName());
			velocityContext.put("message", message);
//			velocityContext.put("url", rb.getString("pswd.login.url"));
			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);
			String body = stringWriter.toString();
			MailQueued mObj = new MailQueued();
			//mObj.setMailFrom(appProperties.getUsername());
			//mObj.setMailTo(user.getEmail());
			mObj.setSubject(subject);
			mObj.setBody(body);
			mObj.setBodyType("HTML");
			mObj.setStatus("QUEUED");
			mObj = mailQueuedRepository.save(mObj);
			if (mObj != null) {
				return true;
			} else {
				logger.error("Mail Object is null in mailPasswordDetails method in MailServiceImpl.......");
				return false;
			}
		} catch (Exception ex) {
			logger.error("Exception occured in mailPasswordDetails method in MailServiceImpl-->"+ex.getMessage());
			return false;
		}
	}
	
	@Transactional
	@Override
	public Boolean mailRegistrationDetails(String templateCode, String mail, MailPriority priority, String... emailValues) {
		try {
			Optional<MailTemplatesMaster> geTemplate = mailTemplateRepository.findByTemplateCode(templateCode);
			if (geTemplate.isPresent()) {
				String body = geTemplate.get().getTemplateBody();
				for (int i = 0; i < emailValues.length; i++) {
					body = body.replace("DYNAMIC_VALUE" + (i + 1), emailValues[i]);
				}
				String subject = geTemplate.get().getTemplateSubject();
				String templateType = geTemplate.get().getTemplateType();

				MailQueued mObj = new MailQueued();
				mObj.setMailFrom(mailUsername);
				mObj.setMailTo(mail);
				mObj.setSubject(subject);
				mObj.setBody(body);
				mObj.setBodyType(templateType);
				mObj.setStatus("QUEUED");
				mObj.setMailPriority(priority.getPriority());
				mObj = mailQueuedRepository.save(mObj);
				if (mObj != null) {
					return true;
				} else {
					logger.error("Mail Object is null in mailPasswordDetails method in MailServiceImpl.......");
					return false;
				}
			} else {
				logger.error("Mail Template is not available in mailPasswordDetails method in MailServiceImpl.......");
				return false;
			}
		} catch (Exception ex) {
			logger.error("Exception occured in mailPasswordDetails method in MailServiceImpl-->"+ex.getMessage());
			return false;
		}
	}










}
