package com.aashdit.setup.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class RoleDto {

	private Long roleId;

	private String roleCode;

	private String displayName;

	private String description;

	private Boolean displayOnPage;

	private Long maxAssignments;

	private Boolean isDesignation;

	private Long roleLevel;

	private Boolean isActive;

	private Boolean isPromotion;

	private Boolean isTeaching;

	private Boolean isSectionApplicable;

	private Boolean isRecruitment;

	private Boolean isAdditional;
}
