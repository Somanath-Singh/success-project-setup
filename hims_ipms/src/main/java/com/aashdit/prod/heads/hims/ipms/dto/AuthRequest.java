package com.aashdit.prod.heads.hims.ipms.dto;

public class AuthRequest {
	private String userName;
	private String password;
	private String captcha;
	private String loginType;
	
	 public AuthRequest() {
	        this.loginType = "PASS_LOGIN";
	    }
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	
}
