package com.aashdit.prod.heads.hims.ipms.utils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AESDecryption {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "1234567891234567";  // 16-byte secret key (same as JS)
    private static final String IV = "1234567890123456";  // 16-byte IV (same as JS)

    // Method to decrypt AES data
    public static String decrypt(String encryptedData, String secretKey) {
        try {
            // Convert secret key and IV into byte arrays
            byte[] key = secretKey.getBytes("UTF-8");
            byte[] iv = IV.getBytes("UTF-8"); // IV (Initialization Vector)

            // Initialize the AES key and IV
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // Initialize the Cipher for AES decryption in CBC mode
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            // Decode the Base64-encoded encrypted data
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);

            // Decrypt the data
            byte[] decryptedBytes = cipher.doFinal(decodedData);

            // Return the decrypted data as a UTF-8 string
            return new String(decryptedBytes, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Example of encrypted data (Base64 encoded)
        String encryptedData = "/CFC6dRYvrRzSEqIOHf42DcSmNDmLSn4yRd2u7mqeK7gL4I96BpfnKDOkeha6TuvNYYos7HeTt1VmmFO1XHL0loFWfi6Rn3KaC2yeUfCViuCgOj/DQ3444WbUCk8NCXDteZV5acmW3s8TX+wUBvU3Qdg3tpd6uFQlvjFtzwXn+f+mWg0NLErjZ1uiO2c4Xz1ptM2jwyJNWgal5xd78sOt1DOQsMe+C+ZO1SAdgHz76JzOFMlCWWuYMlkkWj5FBXhyRIyMzGcd7rg/0hBTWMycpQn0z1xZCYpBlNEYKmbldzVOGW2zhQGzQqTSS4Xbv9TE1BUQAI0DB6SP+ycREV25vIExK2fGjqgMx9ETwrwjqY="; // Replace with the encrypted string from JS

        // Decrypt the data using the same secret key
        String decryptedData = decrypt(encryptedData, SECRET_KEY);
        System.out.println("Decrypted Data: " + decryptedData);
    }
    
    public static String decrypt(String encryptedText) throws Exception {
    	return AESDecryption.decrypt(encryptedText, SECRET_KEY);
    }

}
