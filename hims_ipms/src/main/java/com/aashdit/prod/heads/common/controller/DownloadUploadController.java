package com.aashdit.prod.heads.common.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.aashdit.prod.heads.common.utils.GetPropertyValue;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/document")
@Slf4j
public class DownloadUploadController {

//	function of view the document in another tab
	@PostMapping(value = "/download", name = "")
	public void downloadDocument(String docPath, String module, HttpServletResponse response,
			HttpServletRequest request) {

		String path = GetPropertyValue.getPropertyValue("UPLOAD.FILE.PATH");
		String filePath = path + File.separator + module + File.separator + docPath;
		Path file = Paths.get(filePath);
		try {
			if (!Files.exists(file)) {
				return;
			}
			String mime = Files.probeContentType(file);
			if (mime == null || mime.isEmpty()) {
				mime = "application/octet-stream";
			}
			switch (mime) {
			case "application/pdf":
				response.setContentType("application/pdf");
				break;
			case "application/vnd.ms-excel": // .xls
				response.setContentType("application/vnd.ms-excel");
				break;
			case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet": // .xlsx
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				break;
			case "application/vnd.ms-powerpoint": // .ppt
				response.setContentType("application/vnd.ms-powerpoint");
				break;
			case "application/vnd.openxmlformats-officedocument.presentationml.presentation": // .pptx
				response.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
				break;
			case "application/msword": // .doc
				response.setContentType("application/msword");
				break;
			case "application/vnd.openxmlformats-officedocument.wordprocessingml.document": // .docx
				response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				break;
			case "application/plain":
				response.setContentType("application/plain");
				break;
			case "image/jpg":
				response.setContentType("image/jpg");
				break;
			case "image/JPG":
				response.setContentType("image/JPG");
				break;
			case "image/jpeg":
				response.setContentType("image/jpeg");
				break;
			case "image/png":
				response.setContentType("image/png");
				break;
			case "image/PNG":
				response.setContentType("image/PNG");
				break;
			case "image/gif":
				response.setContentType("image/gif");
				break;
			case "image/GIF":
				response.setContentType("image/GIF");
				break;
			case "image/bmp":
				response.setContentType("image/bmp");
				break;
			case "application/octet-stream":
				response.setContentType("application/octet-stream");
				break;
			default:
				response.setContentType("application/octet-stream");
				break;
			}
			response.addHeader("Content-Disposition", "inline; filename=");
			Files.copy(file, response.getOutputStream());
			response.getOutputStream().flush();
		} catch (Exception ioe) {
			ioe.printStackTrace();
			return;
		}
	}

	// function of upload document
	public String uploadFile(MultipartFile file, String moduleName, String fileName, String fileExtension) {
		String uniqFileName = null;
		try {
			String path = GetPropertyValue.getPropertyValue("UPLOAD.FILE.PATH");
			String filePath = path + File.separator + moduleName;
			fileName = addCurrenDateTimeToDocAndRenameItModified(fileName, fileExtension);
			File checkFolderPath = new File(filePath);
			if (!checkFolderPath.exists()) {
				checkFolderPath.mkdirs();
			}
			Path uploadPath = Paths.get(filePath.concat(File.separator + fileName));
			Files.write(uploadPath, file.getBytes());
			uniqFileName = fileName;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in DownloadUploadController, method --> uploadFile: " + e.getMessage());
		}
		return uniqFileName;
	}

	// function of rename file name with current date & time
	public String addCurrenDateTimeToDocAndRenameItModified(String fileName, String extension) throws IOException {
		try {
			fileName = fileName.replaceAll("\\s+", "");
			LocalDate dt = LocalDate.now();
			LocalDateTime tm = LocalDateTime.now();
			Calendar cal = Calendar.getInstance();
			String meridiem = cal.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault());
			String currdt = "_" + dt.getMonth() + "_" + tm.getNano() + 1 + "_" + meridiem;
			String filePath = fileName + currdt + "." + extension;
			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
