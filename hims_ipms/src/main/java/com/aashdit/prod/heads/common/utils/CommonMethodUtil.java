package com.aashdit.prod.heads.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.HttpServletRequest;

public class CommonMethodUtil {
	public static String getFileExtension(File file) {
		String fileName = "";
		if (file != null) {
			fileName = file.getName();
		}
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}
	public static long getFileSize(File file) {
		long length = file.length();
		return length;
	}
	public static String getIpAddr(HttpServletRequest request) { 

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		return remoteAddr;
	} 
	public static String getMACAddress(String ip){ 
		String str = ""; 
		String macAddress = ""; 
		try { 
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip); 
			InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
			LineNumberReader input = new LineNumberReader(ir); 
			for (int i = 1; i <100; i++) { 
				str = input.readLine(); 
				if (str != null) { 
					if (str.indexOf("MAC Address") > 1) { 
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
						break; 
					} 
				} 
			} 
		} catch (IOException e) { 
			e.printStackTrace(System.out); 
		} 
		return macAddress; 
	}



}
