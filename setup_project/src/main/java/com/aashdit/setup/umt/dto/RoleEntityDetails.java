package com.aashdit.setup.umt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleEntityDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String primaryRoleCode;
	private String[] otherRoleCodes;
}
