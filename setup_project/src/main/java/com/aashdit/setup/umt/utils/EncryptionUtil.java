package com.aashdit.setup.umt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Slf4j
public class EncryptionUtil {

	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	  private static final String ALGORITHM = "AES";
	    private static final String CHARSET = "UTF-8";
	    private static final String key = "HOSTEL"; // Same key as decryption

	    public static <T> String encryptData(T data, boolean doByAESKey, int encryptionRounds) {
	        try {
	            String serializedData = null;

	            if (data instanceof String) {
	              
	                serializedData = (String) data;
	            } else {
	                serializedData = objectMapper.writeValueAsString(data);
	            }
	            String encryptedData = null;
	            if (doByAESKey) {
	                encryptedData = encryptWithAESKey(serializedData);
	            } else {
	 	            
	                byte[] serializedBytes = serializedData.getBytes(StandardCharsets.UTF_8);
	                encryptedData = Base64.getEncoder().encodeToString(serializedBytes);
	            }

	            return encryptedData;
	        } catch (Exception e) {
	            log.error("Error encrypting data: " + e.getMessage(), e);
	            return null;
	        }
	    }
	    
		private static String encryptWithAESKey(String serializedData) {
			AesUtil formAesUtil = new AesUtil(128, 1000);
			
			HttpServletRequest request=getCurrentRequest();
			CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	        String formPsk = csrfToken.getToken();
	        
	        
	     	String key = formPsk.substring(0, 16);
	     	String salt = generateRandomSalt(16);
	        String iv = generateRandomSalt(16);
	        String plaintext = serializedData;

	        String encryptedCiphertext = formAesUtil.encrypt(salt, iv, key, plaintext);
	        
	        String aesData = (iv + "::" + salt + "::" + encryptedCiphertext);

	        byte[] serializedBytes = aesData.getBytes(StandardCharsets.UTF_8);
            String encryptedData = Base64.getEncoder().encodeToString(serializedBytes);
			
			return encryptedData;
		}
		
		private static String generateRandomSalt(int size) {
	        byte[] salt = new byte[size];
	        SecureRandom secureRandom = new SecureRandom();
	        secureRandom.nextBytes(salt);
	        return bytesToHex(salt);
	    }

	    private static String bytesToHex(byte[] bytes) {
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : bytes) {
	            String hex = Integer.toHexString(0xff & b);
	            if (hex.length() == 1) {
	                hexString.append('0');
	            }
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    }
		public static HttpServletRequest getCurrentRequest() {
	        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	        return requestAttributes.getRequest();
	    }
	    
	    public static String encryptToBase64(String plaintext, int encriptionRounds) throws Exception {
	    	encriptionRounds=3;
	    	
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        SecretKeySpec secretKeySpec = generateKey(key); // Generate AES key
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(CHARSET));
	        
	        for (int i = 0; i < encriptionRounds; i++) {
	        	encryptedBytes = cipher.doFinal(encryptedBytes);
            }
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }

	    private static SecretKeySpec generateKey(String key) throws Exception {
	        byte[] keyBytes = padKey(key);
	        return new SecretKeySpec(keyBytes, ALGORITHM);
	    }

	    private static byte[] padKey(String key) throws Exception {
	        MessageDigest sha = MessageDigest.getInstance("SHA-1");
	        byte[] keyBytes = key.getBytes(CHARSET);
	        keyBytes = sha.digest(keyBytes);
	        return Arrays.copyOf(keyBytes, 16); // Truncate or pad the key to 16 bytes
	    }
	    
	   

	

	

}
