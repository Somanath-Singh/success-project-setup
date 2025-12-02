package com.aashdit.prod.heads.common.utils;

import java.security.SecureRandom;

public class RandomAlphaNumeric {

	private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * Generates a random alphanumeric string.
	 *
	 * @param length Length of the string
	 * @return Random alphanumeric string
	 */
	public static String generateRandomAlphaNumeric(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than 0");
		}

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = RANDOM.nextInt(ALPHANUMERIC_CHARS.length());
			sb.append(ALPHANUMERIC_CHARS.charAt(index));
		}
		return sb.toString();
	}

}
