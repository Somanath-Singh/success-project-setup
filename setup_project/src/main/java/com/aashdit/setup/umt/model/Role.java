package com.aashdit.setup.umt.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.aashdit.setup.umt.utils.Auditable;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="t_umt_role", schema = "public")
public class Role extends Auditable<Long> implements Serializable {
	private static final long serialVersionUID = 285701719160134651L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;
	
	@Column(name = "role_code")
	private String roleCode;
	
	
	@Column(name = "display_name")
	private String displayName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "display_on_page")
	private Boolean displayOnPage;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Transient
	private String officeName;

	@Column(name = "max_assignments")
	private Long maxAssignments;

	@Column(name = "is_designation")
	private Boolean isDesignation;

	@Column(name = "role_level")
	private Long roleLevel;

	// default value is 'PUBLIC'
	@Column(name = "role_access_type", columnDefinition = "default 'PUBLIC'")
	private String roleAccessType = "PUBLIC";

	@Column(name = "is_role_appl_for_mobile", columnDefinition = "")
	private Boolean isRoleApplForMobile;
	
	@Transient
	private String objectIdAndType;
	
} 
  