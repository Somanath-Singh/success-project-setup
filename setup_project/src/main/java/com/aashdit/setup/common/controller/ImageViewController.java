package com.aashdit.setup.common.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aashdit.setup.common.utils.DownloadFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/document")
public class ImageViewController {

	ResourceBundle rb1 = ResourceBundle.getBundle("application");
	final ResourceBundle rb = ResourceBundle.getBundle("application-" + rb1.getString("spring.profiles.active"));

	@Value("${UPLOAD.JASPER.FILE.PATH}")
	private String uploadJasperFilePath;

	@Autowired
	private DownloadUploadController downloadUploadController;

	@GetMapping(value = "/viewDocuments", name = "Download Document")
	public void downloadDocuments(Model model, String moduleName, String filePath, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			String path = rb.getString("UPLOAD.FILE.PATH") + File.separator + moduleName + File.separator + filePath;
			Path file = Paths.get(path);
			DownloadFile.viewUploadedDocument(file, request, response);
		} catch (Exception e) {
			log.error("Exception occured in viewDocuments method in ImageViewController where moduleName is ->"
					+ moduleName + " and filePath is->" + filePath + " and exception message is -->" + e.getMessage());
		}
	}

	@ResponseBody
	@GetMapping(value = "/api/allowAll/image/viewDocuments", name = "Download Document")
	public void apiDownloadDocuments(Model model, String moduleName, String filePath, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			String path = rb.getString("DIUP.FILE.PATH") + File.separator + moduleName + File.separator + filePath;
			Path file = Paths.get(path);
			DownloadFile.viewUploadedDocument(file, request, response);
		} catch (Exception e) {
			log.error("Exception occured in apiDownloadDocuments method in ImageViewController where moduleName is ->"
					+ moduleName + " and filePath is->" + filePath + " and exception message is -->" + e.getMessage());
		}
	}

	@GetMapping(value = "/nodalViewDocuments", name = "Download Document")
	public void downloadNADocuments(Model model, String moduleName, String filePath, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			String path = rb.getString("UPLOAD.FILE.PATH") + File.separator + moduleName + File.separator + filePath;
			Path file = Paths.get(path);
			DownloadFile.viewUploadedDocument(file, request, response);
		} catch (Exception e) {
			log.error("Exception occured in viewDocuments method in ImageViewController where moduleName is ->"
					+ moduleName + " and filePath is->" + filePath + " and exception message is -->" + e.getMessage());
		}
	}

	@GetMapping("/view-documents")
	public void viewDocuments(String doc, String module, HttpServletRequest request, HttpServletResponse response) {
		downloadUploadController.downloadDocument(doc, module, response, request);
	}

	@GetMapping(value = "/report/viewDocuments", name = "View Document")
	public void viewDocuments(Model model, String filePath, HttpServletResponse response, HttpServletRequest request) {
		try {
			Path file = Paths.get(uploadJasperFilePath + File.separator + filePath);
			DownloadFile.viewUploadedDocument(file, request, response);
		} catch (Exception e) {
			log.error("Exception occured in viewDocuments method in ImageViewController where  fileName is->" + filePath
					+ " and exception message is -->" + e.getMessage());
		}
	}

	@GetMapping("/report/download-file")
	public ResponseEntity<Resource> downloadFile(@RequestParam("filePath") String filePath) throws IOException {
		Path path = Paths.get(uploadJasperFilePath + File.separator + filePath);
		Resource resource = null;
		resource = new UrlResource(path.toUri());
		return ResponseEntity.ok()
				.contentType(MediaType
						.parseMediaType(FileTypeMap.getDefaultFileTypeMap().getContentType(resource.getFile())))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
