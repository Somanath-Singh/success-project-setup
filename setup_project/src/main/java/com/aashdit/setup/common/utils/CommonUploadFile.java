package com.aashdit.setup.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.setup.core.util.ApplicationDateUtils;
import com.aashdit.setup.umt.utils.CommonHelperFunctions;

public class CommonUploadFile {
	private final static Logger logger = Logger.getLogger(CommonUploadFile.class);

	public static String addCurrenDateTimeToDocAndRenameItModified(String fileName, String extension)
			throws IOException {
		String filePath = "";

		try {
			String currDateTime = CommonHelperFunctions.getStringTodayAsDDMMYY().replaceAll("/", "") + "_"
					+ ApplicationDateUtils.getStringNowAsHrMi24HrFormat().replaceAll(":", "");

			filePath = CommonConstants.CONST_FILEUPLOAD_PREFIX + fileName + "_" + currDateTime + "." + extension;
		} catch (Exception e) {
			logger.error("-- Error -- CommonUploadFile -- addCurrenDateTimeToDocAndRenameItModified() --->>"
					+ ExceptionUtils.getFullStackTrace(e));
		}
		return filePath;
	}

	public static String addCurrenDateTimeToDocAndRenameItModified_OLD(String regCode, String extension)
			throws IOException {
		try {
			LocalDate dt = LocalDate.now();
			LocalDateTime tm = LocalDateTime.now();
			Calendar cal = Calendar.getInstance();
			String meridiem = cal.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault());
			String currdt = "_" + dt.getMonth() + "_" + tm.getNano() + 1 + "_" + meridiem;
			String filePath = regCode + currdt + "." + extension;
			return filePath;
		} catch (Exception e) {
			logger.error("-- Error -- CommonUploadFile -- addCurrenDateTimeToDocAndRenameItModified_OLD() --->>"
					+ ExceptionUtils.getFullStackTrace(e));
			return "";
		}
	}

	public static String uploadDocumentCommon(MultipartFile file, String uploadPathStatic, String module, String code)
			throws IOException {
		String uniqFileName = "";

		try {
			String filePath = uploadPathStatic + File.separator + module;
			if (code.equals("") || code.isEmpty()) {
				filePath = filePath + File.separator + code;
			}

			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			String fname = addCurrenDateTimeToDocAndRenameItModified(code, extension);
			File checkFolderPath = new File(filePath);
			if (!checkFolderPath.exists()) {
				checkFolderPath.mkdirs();
			}

			Path uploadPath = Paths.get(filePath.concat(File.separator + fname));
			Files.write(uploadPath, file.getBytes());
			uniqFileName = fname;
		} catch (Exception e) {
			logger.error("-- Error -- CommonUploadFile -- upload() --->>" + ExceptionUtils.getFullStackTrace(e));
		}
		return uniqFileName;
	}

	public static String getUploadedPath(String path, String module, String code, String filename) {
		String uniqePathName = "";
		try {
			String filePath = path + File.separator + module;
			if (code.equals("") || code.isEmpty()) {
				filePath = filePath + File.separator + code;
			}

			filePath += File.separator + filename;
			uniqePathName = filePath;
		} catch (Exception ex) {
			logger.error(
					"-- Error -- CommonUploadFile -- getUploadedPath() --->>" + ExceptionUtils.getFullStackTrace(ex));
		}
		return uniqePathName;
	}

	public static String finalUpload(String base64File, MultipartFile file, String path, String uniqueCode)
			throws IOException {

		String filePath = "";
		byte[] byteBase64 = null;
		String uniqFileName = null;
		Path uploadPath = null;
		String fileExtension = "";
		String returnData = "";
		try {
			if (file.getOriginalFilename() != null) {
				fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
				Boolean fileSuspicious = null;
				if (fileExtension.equalsIgnoreCase("pdf")) {
					fileSuspicious = new PdfDocumentDetectorImpl().isSafe(file.getInputStream());
				} else {
					fileSuspicious = true;
				}
				if (fileSuspicious) {
//	                    if (path.endsWith("ARTICLE-275" + File.separator)) {
//	                        filePath = path + File.separator + uniqueCode;
//	                    } else {
//	                        filePath = path;
//	                    }

					filePath = path + File.separator + uniqueCode;

					if (Optional.ofNullable(base64File).isPresent()) {
						String[] parts = base64File.split("_");
						String imageString = parts[0];
						String extension = parts[1];
						byteBase64 = Base64.decodeBase64(imageString);
						uniqFileName = addCurrenDateTimeToDocAndRenameItModified(uniqueCode, extension);
					} else {
						byteBase64 = file.getBytes();
						uniqFileName = addCurrenDateTimeToDocAndRenameItModified(uniqueCode,
								FilenameUtils.getExtension(file.getOriginalFilename()));
					}

					File checkFolderPath = new File(filePath);
					if (!checkFolderPath.exists()) {
						checkFolderPath.mkdirs();
					}

					uploadPath = Paths.get(filePath.concat(File.separator + uniqFileName));
					Files.write(uploadPath, byteBase64);
					returnData = uploadPath.getFileName().toString();
				} else {
					returnData = null;
//	                    throw new MyException("One or more suspicious files were found. Unable to save data.");
					logger.error("One or more suspicious files were found. Unable to save data.");
				}
			}
			return returnData;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return returnData;
		}
	}

	public static String getCurrentUserLogoPath(HttpServletRequest request, String module, String docName) {
		String defaultPath = request.getSession().getServletContext().getRealPath("/");

		// Validate the document name
		if (docName == null || docName.isEmpty() || docName.isBlank()) {
			return defaultPath + "/assets/img/aashdit-logo.png"; // Return a web URL instead of a file path
		}

		// Fetch the base upload path
		String basePath = GetPropertyValue.getPropertyValue("UPLOAD.FILE.PATH");

		// Construct the full path securely
		Path filePath = Paths.get(basePath, module, docName);

		// Ensure the file exists
		if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
			return filePath.toString();
		}

		// Fallback to default logo
		return defaultPath + "/assets/img/aashdit-logo.png";
	}

}
