package com.aashdit.prod.heads.common.utils;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtil {
	
	  private static final Logger log = LoggerFactory.getLogger(LoggingUtil.class);
	  /**
		 * @author Debapriya
		 * @ date july 2024
	 */
	    public static void logError(Exception e) {
	        StackTraceElement[] stackTrace = e.getStackTrace();
	        if (stackTrace.length > 0) {
	            StackTraceElement element = stackTrace[0];
	            String className = element.getClassName();
	            String methodName = element.getMethodName();
	            log.error("Error occurs in {}.{}: {}", className, methodName, e.getMessage());
	        } else {
	            log.error("Error: {}", e.getMessage());
	        }
	        e.printStackTrace();
	    }
	    
	    
	    public static void logErrorWithCode(String errorCode, Exception e) {
	        if (e instanceof CustomException) {
	            CustomException customException = (CustomException) e;
	            errorCode = customException.getErrorCode();
	        }
	        StackTraceElement[] stackTrace = e.getStackTrace();
	        if (stackTrace.length > 0) {
	            StackTraceElement element = stackTrace[0];
	            String className = element.getClassName();
	            String methodName = element.getMethodName();
	            log.error("Error code {}: occurs in {}.{}: {}", errorCode, className, methodName, e.getMessage());
	        } else {
	            log.error("Error code {}: {}", errorCode, e.getMessage());
	        }
	        e.printStackTrace();
	    }

	    public static void logErrorWithContext(String message, Exception e) {
	        StackTraceElement[] stackTrace = e.getStackTrace();
	        if (stackTrace.length > 0) {
	            StackTraceElement element = stackTrace[0];
	            String className = element.getClassName();
	            String methodName = element.getMethodName();
	            log.error("{} - Error occurs in {}.{}: {}", message, className, methodName, e.getMessage());
	        } else {
	            log.error("{} - Error: {}", message, e.getMessage());
	        }
	        e.printStackTrace();
	    }
	    
	    public static void logInfo(String message) {
	        log.info(message);
	    }

	    public static void logWarn(String message) {
	        log.warn(message);
	    }

	    public static void logDebug(String message) {
	        log.debug(message);
	    }


		// 2024-04-01 00:00;00.0 convert this format to dd-MM-yyyy
		public static String convertDate(Date date) {
			String dateStr = "";
			try {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				int month = cal.get(Calendar.MONTH) + 1;
				int year = cal.get(Calendar.YEAR);
				dateStr = day + "-" + month + "-" + year;
			} catch (Exception e) {
				logError(e);
			}
			return dateStr;
		}

}
