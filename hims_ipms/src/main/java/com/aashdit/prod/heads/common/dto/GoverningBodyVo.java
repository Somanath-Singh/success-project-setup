package com.aashdit.prod.heads.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoverningBodyVo {
	
	private Long governingBodyId;
	private String governingBodyName;
	private String governingBodyCode;
	private Boolean isActive=true;
	private String remarks;
	private String emailId;
	private String phoneNo;
	private String website;
	private Long pincode;
	private String addressLine;
	private List<Long> moduleIds;
	private String primaryRoleCode;
	private String[] otherRoleCodes;

}
