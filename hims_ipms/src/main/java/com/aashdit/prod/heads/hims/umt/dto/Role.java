package com.aashdit.prod.heads.hims.umt.dto;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 285701719160134651L;
	private Long roleId;
	private String roleCode;
	private String displayName;
	private Integer roleLevel = 0;
} 
  