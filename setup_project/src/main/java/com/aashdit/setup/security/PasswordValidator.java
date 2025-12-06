package com.aashdit.setup.security;

/***
 * @Author Soumyaranjan satapathy
 * @Date 13/01/2023
 **/

public class PasswordValidator {

	@SuppressWarnings("unused")
	public static boolean checkString(String str) {
		char ch;
		boolean capitalFlag = false;
		boolean lowerCaseFlag = false;
		boolean numberFlag = false;
		boolean spCharFlag = false;
		boolean lengthFlag = false;
		boolean allChkFlag = false;

		String specialCharacters = " !#$%&'()*+,-./:;<=>?@[]^_`{|}";
		// ((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})

		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);

			if (Character.isDigit(ch)) {
				numberFlag = true;
			} else if (Character.isUpperCase(ch)) {
				capitalFlag = true;
			} else if (Character.isLowerCase(ch)) {
				lowerCaseFlag = true;
			} else if (specialCharacters.contains(Character.toString(str.charAt(i)))) {
				spCharFlag = true;
			}

			if (numberFlag && capitalFlag && lowerCaseFlag && spCharFlag) {
				allChkFlag = true;
			}
		}
		// System.out.println("password======================"+str.length()+"/"+allChkFlag
		// );
		if (str.length() >= 8 && allChkFlag) {
			return true;
		} else {
			return false;
		}
	}
}
