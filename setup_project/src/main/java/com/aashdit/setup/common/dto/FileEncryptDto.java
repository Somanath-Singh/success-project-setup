package com.aashdit.setup.common.dto;

import javax.persistence.Transient;

import lombok.Data;

@Data
public class FileEncryptDto {

	private String fileName;
	private String moduleName;
	@Transient
	private String _csrf;
	@Transient
	private String cipherText;

}
