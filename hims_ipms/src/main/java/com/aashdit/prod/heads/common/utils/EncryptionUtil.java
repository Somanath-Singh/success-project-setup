package com.aashdit.prod.heads.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for AES encryption and decryption.
 *
 * Industry standard: Avoid hardcoding secrets in code.
 * Load from environment variables, application.yml, or a vault.
 *
 * @author Somanath Singh
 * @since 18/08/2025
 */
@Slf4j
public final class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    /**
     * Hardcoded secret key (16 bytes required for AES-128).
     * Change it carefully — must be 16, 24, or 32 bytes for AES.
     */
    private static final String SECRET = "MyFixedSecretKey"; 

    private static final SecretKeySpec SECRET_KEY = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);

    // Prevent instantiation
    private EncryptionUtil() {
    }

    /**
     * Encrypts a plain string using AES and encodes with Base64 URL-safe encoding.
     *
     * @param plainText the value to encrypt (e.g., database ID)
     * @return encrypted value
     */
    public static String encrypt(String plainText) {
        try {
        	
        	if (plainText == null || plainText.isBlank()) {
                throw new EncryptionException("plain text is null or empty", null);
            }

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            String encrypted = Base64.getUrlEncoder().encodeToString(encryptedBytes);

            log.debug("Successfully encrypted value of length {}.", plainText.length());
            return encrypted;
        } catch (GeneralSecurityException e) {
            log.error("Encryption failed for value: {}. Error: {}", plainText, e.getMessage());
            e.printStackTrace();
            throw new EncryptionException("Failed to encrypt value", e);
        }
    }

    /**
     * Decrypts an encrypted string back into a Long.
     *
     * @param encryptedText the encrypted value
     * @return decrypted Long ID
     */
    public static Long decryptToLong(String encryptedText) {
        try {
        	
        	 if (encryptedText == null || encryptedText.isBlank()) {
                 throw new EncryptionException("Encrypted text is null or empty", null);
             }

             // Check if input looks like Base64
             if (!encryptedText.matches("^[A-Za-z0-9_-]+$")) {
                 throw new EncryptionException("Invalid encrypted text format: " + encryptedText, null);
             }
             
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            byte[] decryptedBytes = cipher.doFinal(Base64.getUrlDecoder().decode(encryptedText));
            String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

            Long result = Long.parseLong(decryptedString);
            log.debug("Successfully decrypted to Long.");
            return result;
        } catch (GeneralSecurityException | NumberFormatException e) {
            log.error("Decryption failed for value: {}. Error: {}", encryptedText, e.getMessage());
            e.printStackTrace();
            throw new EncryptionException("Failed to decrypt value to Long", e);
        }
    }

    /**
     * Decrypts an encrypted string back into plain text.
     *
     * @param encryptedText the encrypted value
     * @return decrypted plain string
     */
    public static String decryptToString(String encryptedText) {
        try {
        	
        	if (encryptedText == null || encryptedText.isBlank()) {
                throw new EncryptionException("Encrypted text is null or empty", null);
            }

            // Check if input looks like Base64
            if (!encryptedText.matches("^[A-Za-z0-9_-]+$")) {
                throw new EncryptionException("Invalid encrypted text format: " + encryptedText, null);
            }
        	
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            byte[] decryptedBytes = cipher.doFinal(Base64.getUrlDecoder().decode(encryptedText));
            String decrypted = new String(decryptedBytes, StandardCharsets.UTF_8);

            log.debug("Successfully decrypted string of length {}.", decrypted.length());
            return decrypted;
        } catch (GeneralSecurityException e) {
            log.error("Decryption failed for value: {}. Error: {}", encryptedText, e.getMessage());
            e.printStackTrace();
            throw new EncryptionException("Failed to decrypt value to String", e);
        }
    }

    /**
     * Custom runtime exception for encryption/decryption errors.
     */
    public static class EncryptionException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public EncryptionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
