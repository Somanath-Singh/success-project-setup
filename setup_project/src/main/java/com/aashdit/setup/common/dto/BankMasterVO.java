package com.aashdit.setup.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BankMasterVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bankId;
	private String bankName;
	private String shortName;
}
