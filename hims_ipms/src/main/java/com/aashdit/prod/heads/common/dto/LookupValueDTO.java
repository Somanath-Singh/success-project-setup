package com.aashdit.prod.heads.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupValueDTO implements Serializable {
	private static final long serialVersionUID = -1690076517522282465L;

	private Long valueId;
	private String valueCode;
	private String code;
	private String valueEn;
	private String valueHi;
	private Boolean isActive;
}