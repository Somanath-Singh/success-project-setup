package com.aashdit.prod.heads.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class MunicipalityVo {
	
	private Long municipalityId;
	private String municipalityName;
	private String municipalityCode;
	private Long district;
	private String objectIdAndType;
	private Boolean isActive=true;
	private String remarks;
	private String emailId;
	private String phoneNo;
	private String website;
	private Long pincode;
	private String addressLine;
	private List<Long> moduleIds;
	private String primaryRoleCode;
	
}
