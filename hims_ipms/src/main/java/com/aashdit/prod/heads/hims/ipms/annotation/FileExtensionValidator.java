package com.aashdit.prod.heads.hims.ipms.annotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {
	private long maxSize;
	private List<String> allowedExtensions;
	private String customMessage;
	private boolean mandatory;
	private static final Logger LOGGER = Logger.getLogger(FileExtensionValidator.class.getName());

	private static final List<Pattern> DISALLOWED_PATTERNS = Arrays.asList(
			Pattern.compile("<script>", Pattern.CASE_INSENSITIVE),
			Pattern.compile("eval\\((.*)\\)", Pattern.CASE_INSENSITIVE),
			Pattern.compile("/JavaScript", Pattern.CASE_INSENSITIVE),
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
		/// System.out.println("FileExtensionValidator initialized");
		this.maxSize = constraintAnnotation.maxSize();
		this.allowedExtensions = Arrays.asList(constraintAnnotation.allowedExtensions());
		this.customMessage = constraintAnnotation.customMessage();
		this.mandatory = constraintAnnotation.mandatory();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		// System.out.println("Validator is being invoked.");
		// Check if the file is mandatory or not
		if (!mandatory && (file == null || file.isEmpty())) {
			return true;
		}

		// File is required if mandatory
		if (file == null || file.isEmpty()) {
			return addConstraintViolation(context, "The file is required and cannot be empty.");
		}

		// Validate file size (convert bytes to MB)
		double maxSizeMB = maxSize / (1024.0 * 1024.0);
		if (file.getSize() > maxSize) {
			return addConstraintViolation(context,
					String.format("The file size exceeds the maximum allowed limit of %.2f MB.", maxSizeMB));
		}

		// Validate file content safety
		if (!isSafeContent(file)) {
			return addConstraintViolation(context, "The file contains potentially harmful or disallowed content.");
		}

		// Validate file extension
		String fileExtension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
		if (fileExtension.isEmpty() || !allowedExtensions.contains(fileExtension.toLowerCase())) {
			return addConstraintViolation(context,
					customMessage.isEmpty()
							? String.format("The file extension '%s' is not allowed. Allowed extensions are: %s.",
									fileExtension, String.join(", ", allowedExtensions))
							: customMessage);
		}

		// Validate for double extensions
		if (hasDoubleExtension(Objects.requireNonNull(file.getOriginalFilename()))) {
			return addConstraintViolation(context, "The file has a double extension which is not allowed.");
		}

		// Validate for embedded images and encryption in PDFs
		if ("pdf".equals(fileExtension) && (isEncryptedPdf(file))) {
			return addConstraintViolation(context, "The PDF file is encrypted, which is not allowed.");
		}

		// Validate image files for correct format
		if (isImageFile(fileExtension) && !isSafeImage(file)) {
			return addConstraintViolation(context, "The image file is potentially harmful or malformed.");
		}

		// All checks passed, the file is valid
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

	private boolean hasDoubleExtension(String filename) {
		return filename.chars().filter(ch -> ch == '.').count() > 1;
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

	private boolean isEncryptedPdf(MultipartFile file) {
		try (InputStream inputStream = file.getInputStream()) {
			byte[] headerBytes = new byte[5];
			if (inputStream.read(headerBytes) != 5) {
				return false;
			}
			String header = new String(headerBytes);
			if ("%PDF-".equals(header)) {
				byte[] buffer = new byte[1024];
				while (inputStream.read(buffer) != -1) {
					String content = new String(buffer);
					if (content.contains("/Encrypt")) {
						LOGGER.log(Level.WARNING, "Encrypted PDF found: {0}", file.getOriginalFilename());
						return true;
					}
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error checking PDF encryption: {0}", e.getMessage());
		}
		return false;
	}

	private boolean isImageFile(String fileExtension) {
		return Arrays.asList("png", "jpg", "jpeg", "webp").contains(fileExtension.toLowerCase());
	}

	private boolean isSafeImage(MultipartFile file) {
		try (InputStream inputStream = file.getInputStream()) {
			byte[] headerBytes = new byte[8];
			if (inputStream.read(headerBytes) != 8) {
				return false;
			}

			// Check common image file headers
			String headerHex = bytesToHex(headerBytes);
			if (headerHex.startsWith("FFD8FF")) { // JPEG
				return true;
			} else if (headerHex.startsWith("89504E47")) { // PNG
				return true;
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error validating image file: {0}", e.getMessage());
		}
		return false;
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(String.format("%02X", b));
		}
		return hexString.toString();
	}
}
