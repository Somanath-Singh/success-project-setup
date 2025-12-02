package com.aashdit.prod.heads.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ItemOfWorkDto {
	
    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    
}

