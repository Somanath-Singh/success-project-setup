package com.aashdit.prod.heads.common.dto;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aashdit.prod.heads.common.utils.ModuleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GenerateTopCodeDTO {

	@NotNull(message = "Module type is required")
	@Enumerated // Optional: for enum validation
	private ModuleType moduleType;

	@NotNull(message = "District ID is required")
	private Long districtId;

	@NotNull(message = "Block ID is required")
	private Long blockId;

	@NotNull(message = "GP ID is required")
	private Long gpId;

	@NotNull(message = "Village ID is required")
	private Long villageId;
	
	@NotNull(message = "Municipalaty ID is required")
	private Long municipalatyId;
	
	@NotNull(message = "Ward ID is required")
	private Long wardId;
	
	@NotNull(message = "Code Length is required")
	private Integer codeLength = 3;

	@NotNull(message = "Project type name is required")
	@Size(min = 1, max = 100, message = "Project type name must be between 1-100 characters")
	private String projectType;

}
