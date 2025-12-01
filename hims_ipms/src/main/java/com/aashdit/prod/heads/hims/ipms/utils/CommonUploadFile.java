package com.aashdit.prod.heads.hims.ipms.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.io.FilenameUtils;
import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

public class CommonUploadFile {

	private final static Logger logger = Logger.getLogger(CommonUploadFile.class);

	public static String addCurrenDateTimeToDocAndRenameItModified(String regCode, String extension)
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
			logger.error(e.getMessage());
			return "";
		}
	}

	public static String upload(MultipartFile file, String path, String module, String code) throws IOException {
		String uniqFileName = "";
		try {
			String filePath = path + File.separator + module;
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
			logger.error(e.getMessage());
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
			logger.error(ex.getMessage());
		}
		return uniqePathName;
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
			// logger.error("-- Error -- CommonUploadFile -- upload()
			// --->>"+ExceptionUtils.getFullStackTrace(e));
		}
		return uniqFileName;
	}

}
