package com.aashdit.setup.umt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EntityHierarchy implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String levelType;
    private String levelName;
    private String levelIdAndType;
    private String levelIdAndTypeAndName;
    private Boolean isSelected = false;
    private String mappingType;

}
