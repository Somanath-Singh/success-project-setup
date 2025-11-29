package com.aashdit.prod.heads.hims.ipms.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class CommonUtils {
	
	 public static String getFileExtension(File file) {
			String fileName = "";
			if (file != null) {
				fileName = file.getName();
			}
			if (fileName.indexOf(".") != -1 && fileName.indexOf(".") != 0)
				return fileName.substring(fileName.indexOf(".") + 1);
			else
				return "";
		}
	    
	    public static boolean isFileValid(MultipartFile file, RedirectAttributes message) throws IOException {
		    String attachment = getFileExtension(new File(file.getOriginalFilename()));
		    
		    if (attachment.equalsIgnoreCase("pdf")) {
		    	byte[]initialArray = file.getBytes();
		    	InputStream inputStream =  new ByteArrayInputStream(initialArray);
		        boolean fileSuspicious = (new PdfDocumentDetectorImpl()).isSafe(inputStream);
		        if (!fileSuspicious) {
		            message.addFlashAttribute(ApplicationConstants.ERROR_MSG, "One or more suspicious files were found. Unable to save data.");
		            return false;
		        }
		    } else if (!(attachment.equalsIgnoreCase("png") || attachment.equalsIgnoreCase("jpg") || attachment.equalsIgnoreCase("jpeg"))) {
		        message.addFlashAttribute(ApplicationConstants.ERROR_MSG, "Please upload a pdf, jpg, or png document.");
		        return false;
		    }
		    
		    return true;
		}

}
