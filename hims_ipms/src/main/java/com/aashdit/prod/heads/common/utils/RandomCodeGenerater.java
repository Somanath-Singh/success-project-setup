package com.aashdit.prod.heads.common.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCodeGenerater {
	
    public static String getRandomUniqueCode(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int asciiValue = random.nextInt(26) + 'A';
            sb.append((char) asciiValue);
        }
        return sb.toString();
    }
    
    public static String uniqueCodeGenerator(String prefix, Random rand) {
		return prefix + "_"
				+ rand.ints(48, 100).filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96)).limit(6)
						.mapToObj(c -> (char) c)
						.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}
    
    public static String generateUniqueId(String prefix, int numberOfDigits) {
        Random random = new Random();
        String randomNumber = IntStream.range(0, numberOfDigits)
                                       .mapToObj(i -> String.valueOf(random.nextInt(10)))
                                       .collect(Collectors.joining(""));
        return prefix + randomNumber;
    }

}

