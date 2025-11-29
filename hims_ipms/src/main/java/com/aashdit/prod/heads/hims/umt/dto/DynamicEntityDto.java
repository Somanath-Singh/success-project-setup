package com.aashdit.prod.heads.hims.umt.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DynamicEntityDto {

	private Long roleRightLvlId;
	private String entityIds;
	private String entityName;
	private String entityType;
	private String entityLevelStr;
	private Map<String, String> furtherDetails;

}
