package com.aashdit.prod.heads.hims.ipms.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecryptedDataResolver implements HandlerMethodArgumentResolver {

	private static final String SECRET_KEY = "1234567891234567";
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private static final String CHARSET = "UTF-8";

	private final ObjectMapper objectMapper;

	public DecryptedDataResolver(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(DecryptedData.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String encData = webRequest.getParameter("encryptedData");
		Class<?> targetType = parameter.getParameterAnnotation(DecryptedData.class).value();
		try {
			String decryptDate = AESDecryption.decrypt(encData, SECRET_KEY);
			log.info(decryptDate);
			// ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(decryptDate, targetType);
		} catch (IOException e) {
			throw new RuntimeException("Error occurred while parsing decrypted data", e);
		}
	}

	public static SecretKeySpec getSecretKeySpec(String secretKey) throws Exception {
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
		key = sha.digest(key);
		key = java.util.Arrays.copyOf(key, 16); // Use only first 128 bit
		return new SecretKeySpec(key, "AES");
	}

	public static String decrypt(String encryptedText, String secretKey) throws Exception {
		SecretKeySpec keySpec = getSecretKeySpec(secretKey);

		byte[] iv = new byte[16]; // Assuming a 16-byte IV, usually you will retrieve it from the encrypted data
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

		byte[] decodedEncryptedText = Base64.getDecoder().decode(encryptedText);
		byte[] decryptedBytes = cipher.doFinal(decodedEncryptedText);

		return new String(decryptedBytes, CHARSET);
	}

}
