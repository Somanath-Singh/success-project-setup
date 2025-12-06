package com.aashdit.setup.umt.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class NativeQueryResultsParent implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String entityName;
    private String entityCode;
    private String idAndEntityCode;
    private Long parentEntityId;
    private String parentEntityCode;
    private String parentEntityIdAndCode;
    private Boolean isSelected = false;
    private String mappingType;
}
