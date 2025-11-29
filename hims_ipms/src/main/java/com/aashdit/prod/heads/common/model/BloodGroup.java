package com.aashdit.prod.heads.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "t_mst_blood_group")
public class BloodGroup implements Serializable {
	
	private static final long serialVersionUID = 832436664155432699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blood_group_id")
	private Long bloodGroupId;

	@NotNull
	@Column(name = "blood_group_name")
	private String bloodGroupName;
	
	@NotNull
	@Column(name = "blood_group_description")
	private String bloodGroupDescription;

	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;
	
	@Column(name = "last_updated_by")
	private Long lastUpdatedBy;

}
