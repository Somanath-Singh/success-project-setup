package com.aashdit.setup.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;

import com.aashdit.setup.umt.utils.AesUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Java String Encryption Decryption Example
 *
 * @author Ajay Kumar
 */

@Slf4j
public class AESEncryptionDecryption {
	private static final String ALGORITHM = "AES";
	private static final String SECRET_KEY = "!@xksjdcn$%nkvnc";
	private static SecretKeySpec secretKey;
	private static final String CMCSecretKey = "cmc#$!@&*";

	public static String decryptUserRequestedData(String encData, String projectCode) {
		String encryptedString = "";
		try {
			AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
			encryptedString = aesEncryptionDecryption.decrypt(encData, CMCSecretKey);

		} catch (Exception e) {
			return null;
		}
		return encryptedString;

	}

	public static String encrypt(String data) throws Exception {
		DESKeySpec keySpec = new DESKeySpec(SECRET_KEY.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(keySpec);

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public static String decrypt(String encryptedData) throws Exception {
		DESKeySpec keySpec = new DESKeySpec(SECRET_KEY.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(keySpec);

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}

	public void prepareSecreteKey(String myKey) {
		MessageDigest sha = null;
		try {
			byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			log.error("Error while preparing secret key: " + e);
		}
	}

	public String encrypt(String strToEncrypt, String secret) {
		try {
			prepareSecreteKey(secret);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			log.error("Error while encrypting: " + e);
		}
		return null;
	}

	public String decrypt(String strToDecrypt, String secret) {
		try {
			prepareSecreteKey(secret);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			log.error("Error while decrypting: " + e);
		}
		return null;
	}

	public static String decryptURL(final String encryptedString) {

		byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);

		try {
			Cipher cipher = Cipher.getInstance("AES");
			SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
			cipher.init(Cipher.DECRYPT_MODE, originalKey);
			byte[] cipherText = cipher.doFinal(Base64.getUrlDecoder().decode(encryptedString));
			return new String(cipherText);
		} catch (Exception e) {
			throw new RuntimeException("Error occured while decrypting data", e);
		}
	}

	public static String decodeAES(String cipherText, HttpServletRequest request) {
		String realFormData = "";
		try {
			String encFormData = new String(java.util.Base64.getDecoder().decode(cipherText));
			AesUtil formAesUtil = new AesUtil(128, 1000);
			CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			String formPsk = csrfToken.getToken();
			formPsk = formPsk.substring(0, 16);
			if (encFormData != null && encFormData.split("::").length == 3) {
				realFormData = formAesUtil.decrypt(encFormData.split("::")[1], encFormData.split("::")[0], formPsk,
						encFormData.split("::")[2]);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error occured while decrypting data", e);
		}
		return realFormData;
	}

	public static String encryptLong(long value) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		String paddedKey = padKey(SECRET_KEY);
		SecretKeySpec secretKey = new SecretKeySpec(paddedKey.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		String dataString = String.valueOf(value);
		byte[] encryptedBytes = cipher.doFinal(dataString.getBytes("UTF-8"));
		String hexEncoded = bytesToHex(encryptedBytes);
		return URLEncoder.encode(hexEncoded, "UTF-8");
	}

	public static long decryptLong(String encryptedValue) throws Exception {
		String hexEncoded = URLDecoder.decode(encryptedValue, "UTF-8");
		byte[] encryptedBytes = hexToBytes(hexEncoded);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		String paddedKey = padKey(SECRET_KEY);
		SecretKeySpec secretKey = new SecretKeySpec(paddedKey.getBytes(), "AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		String decryptedString = new String(decryptedBytes, "UTF-8");
		return Long.parseLong(decryptedString);
	}

	private static String padKey(String key) {
		StringBuilder paddedKey = new StringBuilder(key);
		while (paddedKey.length() < 16) {
			paddedKey.append(" "); // Pad with spaces
		}
		return paddedKey.toString();
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public static byte[] hexToBytes(String hexString) {
		int len = hexString.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
					+ Character.digit(hexString.charAt(i + 1), 16));
		}
		return data;
	}
}
