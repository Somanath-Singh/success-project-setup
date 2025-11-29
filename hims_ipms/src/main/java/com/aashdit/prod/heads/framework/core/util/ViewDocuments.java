package com.aashdit.prod.heads.framework.core.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

public class ViewDocuments {

	private static final Logger log = Logger.getLogger(ViewDocuments.class);
	
	public static void viewUploadedDocument(String path, HttpServletRequest request, HttpServletResponse response) {

		try {
			Path file = Paths.get(path);
			String mime = Files.probeContentType((java.nio.file.Path) file);
			response.setContentType(mime);
			response.addHeader("Content-Disposition", "inline; filename=");
			
			Files.copy(file, response.getOutputStream());
			response.getOutputStream().flush();
		} catch (Exception ioe) {
			//ioe.printStackTrace();
			log.error("Could not locate file " + path);
			return;
		}

	}

	public static Object encodeUploadedDocument(String filePath) {
		 try {
		        File file = new File(filePath);
		        byte[] fileBytes = Files.readAllBytes(file.toPath());
		        String base64File = Base64.getEncoder().encodeToString(fileBytes);
		        String mimeType = Files.probeContentType(file.toPath());
		        if (mimeType == null) {
		            mimeType = "application/octet-stream"; 
		        }
		        return "data:" + mimeType + ";base64," + base64File;
		    } catch (Exception e) {
		        log.error("Unexpected error while encoding file: " + filePath, e);
		    }
		    return null; 
	}

}
	