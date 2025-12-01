package com.aashdit.prod.heads.common.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@Slf4j
public class CommonUtils {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    /**
     * Handles binding errors and populates the model with error information.
     *
     * @param bindingResult the result of the binding process
     * @param model         the model to be populated with error information
     * @param redirectUrl   the URL to redirect to in case of errors
     * @return the view name for error display or null if no errors
     */
    public static String handleBindingErrors(BindingResult bindingResult, Model model, String redirectUrl) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();

            // Loop through field errors and collect error messages
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String defaultMessage = fieldError.getDefaultMessage();
                errors.add(defaultMessage);
                log.error("Field error in {}: {}", fieldError.getField(), defaultMessage);
            }
            if (!redirectUrl.startsWith("/")) {
                redirectUrl = "/" + redirectUrl;
            }

            // Add error information to the model
            model.addAttribute("error_msg", errors);
            model.addAttribute("code", HttpStatus.BAD_REQUEST.value());
            model.addAttribute("url", StringUtils.isEmpty(redirectUrl) ? "/" : redirectUrl);


            // Return the view name for error display
            return "site.400.error";
        }

        // No errors, return null to indicate success
        return null;
    }

    //use for transfer distance calculate
    public static String distanceBetweenLatAndLongOf2Places(double lat1, double long1, double lat2, double long2) 
	{
		DecimalFormat dcFormat = new DecimalFormat("#,##,###.0");
		String strCalculatedDistanceInKms = "0.00";
		
		try
		{
			// Convert latitude and longitude to radians
			double latRad1 = Math.toRadians(lat1);
			double longRad1 = Math.toRadians(long1);
			double latRad2 = Math.toRadians(lat2);
			double longRad2 = Math.toRadians(long2);
	
			// Earth's mean radius in kilometers
			double earthRadius = 6371.01;
			
			double distanceCalculated = earthRadius * Math.acos(Math.sin(latRad1) * Math.sin(latRad2) + Math.cos(latRad1) * Math.cos(latRad2) * Math.cos(longRad1 - longRad2));
			
			strCalculatedDistanceInKms = dcFormat.format(distanceCalculated);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		// Calculate the distance using the haversine formula
		return strCalculatedDistanceInKms;
    }

    /**
     * Validates the JSON data by deserializing it into the specified class type and performing bean validation.
     *
     * @param <T> the type of the object to be validated
     * @param jsonData the JSON data to be deserialized and validated
     * @param clazz the class type into which the JSON data will be deserialized
     * @param model the Spring model to store validation errors
     * @return a string indicating the error view if there are validation errors, otherwise null
     */
    public static <T> Boolean validateEncryptJsonData(String jsonData, Class<T> clazz, Model model, RedirectAttributes redirectAttributes) {
        // Deserialize JSON to the specified class type
        T object = new Gson().fromJson(jsonData, clazz);

        // Validate the deserialized object
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        // Handle validation errors
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<T> violation : violations) {
            // Store field name and error message in the error map
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        // If there are errors, add them to the model and return error view
        if (!errors.isEmpty()) {
            if(redirectAttributes!=null){
                redirectAttributes.addFlashAttribute("error_msg", errors);
            } else if (model!=null) {
                model.addAttribute("error_msg", errors);
            }
            return true; // Return true to indicate error
        }
        // No errors, return false to indicate success
        return false;
    }
    
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
	    
//	    if (attachment.equalsIgnoreCase("pdf")) {
//	        boolean fileSuspicious = (new PdfDocumentDetectorImpl()).isSafe(file.getInputStream());
//	        if (!fileSuspicious) {
//	            message.addFlashAttribute(ApplicationConstants.ERROR_MSG, "One or more suspicious files were found. Unable to save data.");
//	            return false;
//	        }
//	    } else if (!(attachment.equalsIgnoreCase("png") || attachment.equalsIgnoreCase("jpg") || attachment.equalsIgnoreCase("jpeg"))) {
//	        message.addFlashAttribute(ApplicationConstants.ERROR_MSG, "Please upload a pdf, jpg, or png document.");
//	        return false;
//	    }
	    
	    return true;
	}
}
