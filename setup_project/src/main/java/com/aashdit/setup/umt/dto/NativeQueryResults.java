package com.aashdit.setup.umt.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class NativeQueryResults implements Serializable {
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
	private List<NativeQueryResultsParent> parentData = new ArrayList<>();
	private Boolean isSelected = false;

}
