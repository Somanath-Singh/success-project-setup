package com.aashdit.setup.annotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileExtensionArrayValidator implements ConstraintValidator<FileExtension, MultipartFile[]> {
	private long maxSize;
	private List<String> allowedExtensions;
	private String customMessage;
	private boolean mandatory;
	private static final Logger LOGGER = Logger.getLogger(FileExtensionArrayValidator.class.getName());

	private static final List<Pattern> DISALLOWED_PATTERNS = Arrays.asList(
			Pattern.compile("<script>", Pattern.CASE_INSENSITIVE),
			Pattern.compile("eval\\((.*)\\)", Pattern.CASE_INSENSITIVE),
			// Pattern.compile("<.*?>", Pattern.CASE_INSENSITIVE),
			Pattern.compile("style=[\"'].*?\"", Pattern.CASE_INSENSITIVE),
			Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE),
			Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
			Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
			Pattern.compile("on\\w+\\s*=\\s*\"[^\"]*\"", Pattern.CASE_INSENSITIVE),
			Pattern.compile("iframe", Pattern.CASE_INSENSITIVE),
			Pattern.compile("document\\.cookie", Pattern.CASE_INSENSITIVE),
			Pattern.compile("document\\.write", Pattern.CASE_INSENSITIVE),
			Pattern.compile("window\\.location", Pattern.CASE_INSENSITIVE));

	@Override
	public void initialize(FileExtension constraintAnnotation) {
		this.maxSize = constraintAnnotation.maxSize();
		this.allowedExtensions = Arrays.asList(constraintAnnotation.allowedExtensions());
		this.customMessage = constraintAnnotation.customMessage();
		this.mandatory = constraintAnnotation.mandatory();
	}

	@Override
	public boolean isValid(MultipartFile[] files, ConstraintValidatorContext context) {
		if (!mandatory && (files == null || files.length == 0
				|| Arrays.stream(files).allMatch(file -> !StringUtils.hasText(file.getOriginalFilename())))) {
			return true;
		}

		if (files == null || files.length == 0) {
			return addConstraintViolation(context, "Files are required.");
		}

		for (MultipartFile file : files) {
			if (file == null || file.isEmpty()) {
				return addConstraintViolation(context, "File is empty.");
			}

			if (file.getSize() > maxSize) {
				return addConstraintViolation(context, "File size exceeds the maximum allowed limit.");
			}

			String fileExtension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
			if (fileExtension.isEmpty() || !allowedExtensions.contains(fileExtension.toLowerCase())) {
				return addConstraintViolation(context,
						customMessage.isEmpty() ? "Invalid file extension." : customMessage);
			}

			if (!isSafeContent(file)) {
				return addConstraintViolation(context, "File contains disallowed content.");
			}
		}

		return true;
	}

	private boolean addConstraintViolation(ConstraintValidatorContext context, String message) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		return false;
	}

	private String getFileExtension(String filename) {
		int lastDotIndex = filename.lastIndexOf('.');
		if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
			return "";
		}
		return filename.substring(lastDotIndex + 1);
	}

	private boolean isSafeContent(MultipartFile file) {
		String fileExtension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename())).toLowerCase();
		if ("pdf".equals(fileExtension)) {
			return checkPdfContent(file);
		}
		return true;
	}

	private boolean checkPdfContent(MultipartFile file) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				for (Pattern pattern : DISALLOWED_PATTERNS) {
					if (pattern.matcher(line).find()) {
						LOGGER.log(Level.WARNING, "Disallowed content found in file: {0}", file.getOriginalFilename());
						return false;
					}
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error reading file content: {0}", e.getMessage());
			return false;
		}
		return true;
	}
}
