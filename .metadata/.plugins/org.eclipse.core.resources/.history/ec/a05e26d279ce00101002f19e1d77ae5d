package com.aashdit.prod.heads.hims.ipms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aashdit.prod.heads.framework.core.model.Auditable;
import com.aashdit.prod.heads.hims.umt.model.User;

import lombok.Data;

@Data
@Entity
@Table(name="t_website_mst_content_type")
public class WebsiteContentType extends Auditable<User> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="content_type_id ")
	private Long contentTypeId;
	
	@Column(name="content_type_name")
	private String contentTypeName;
	
	@Column(name="content_type_code")
	private String  contentTypeCode;
	
	@Column(name="is_active")
	private Boolean isActive=true;

}
