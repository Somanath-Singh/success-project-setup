package com.aashdit.setup.core.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailServiceUtil {

	private static final Logger logger = Logger.getLogger(MailServiceUtil.class);

	@Autowired
	private HeadsAppProperties appProperties;

	/**
	 * Sends an email using SMTP configuration.
	 *
	 * @param body     Email content (plain text or HTML)
	 * @param subject  Email subject
	 * @param mailTo   Recipient email address
	 * @param mailFrom Sender email address
	 * @param bodyType "TEXT" or "HTML"
	 * @return true if sent successfully, false otherwise
	 */
	public boolean sendMail(String body, String subject, String mailTo, final String mailFrom, String bodyType) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", appProperties.getAuth());
			props.put("mail.smtp.starttls.enable", appProperties.getEnable());
			props.put("mail.smtp.host", appProperties.getHost());
			props.put("mail.smtp.ssl.trust", appProperties.getTrust());
			props.put("mail.smtp.port", appProperties.getPort());
			props.put("mail.smtp.ssl.protocols", appProperties.getProtocol());

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(appProperties.getUsername(), appProperties.getAppPassword());
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			message.setSubject(subject);

			if ("TEXT".equalsIgnoreCase(bodyType)) {
				message.setText(body);
			} else {
				message.setContent(body, "text/html; charset=UTF-8");
			}

			Transport.send(message);
			logger.infof("Email sent successfully to %s", mailTo);
			return true;

		} catch (MessagingException ex) {
			logger.errorf(ex, "Failed to send email to %s: %s", mailTo, ex.getMessage());
			return false;
		} catch (Exception e) {
			logger.errorf(e, "Unexpected error while sending email to %s", mailTo);
			return false;
		}
	}
}
