package com.aashdit.prod.heads.hims.ipms.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecryptCipherText {

	public static String decryptCipherText(String cipherText, HttpServletRequest request) {
		String realFormData = "";
		try {
			String encFormData = new String(java.util.Base64.getDecoder().decode(cipherText));
			String formPsk = request.getParameter("_csrf");
			formPsk = formPsk.substring(0, 16);
			AesUtil formAesUtil = new AesUtil(128, 1000);
			if (encFormData != null && encFormData.split("::").length == 3)
				realFormData = formAesUtil.decrypt(encFormData.split("::")[1], encFormData.split("::")[0], formPsk,
						encFormData.split("::")[2]);
		} catch (Exception e) {
			log.error("Error decrypting cipher text: " + e.getMessage());
		}
		return realFormData;
	}

	public static String decryptCipherTextGet(String cipherText, HttpServletRequest request) {
		String realFormData = "";
		try {
			String encFormData = new String(java.util.Base64.getDecoder().decode(cipherText));
			CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			String formPsk = csrfToken.getToken().substring(0, 16);
			AesUtil formAesUtil = new AesUtil(128, 1000);
			if (encFormData != null && encFormData.split("::").length == 3)
				realFormData = formAesUtil.decrypt(encFormData.split("::")[1], encFormData.split("::")[0], formPsk,
						encFormData.split("::")[2]);
		} catch (Exception e) {
			log.error("Error decrypting cipher text: " + e.getMessage());
		}
		return realFormData;
	}

}
