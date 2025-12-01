package com.aashdit.prod.heads.hims.ipms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "t_mst_application_module", schema = "public")
public class ApplicationModuleMst implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "module_name")
	private String moduleName;

	@Column(name = "module_code", unique = true, nullable = false)
	private String moduleCode;

	@Column(name = "module_description")
	private String moduleDescription;

	@Column(name = "module_icon")
	private String moduleIcon;

	@Column(name = "is_active", columnDefinition = "boolean default true")
	private Boolean isActive = true;

	@Column(name = "display_order", columnDefinition = "integer default 0")
	private Integer displayOrder = 0;

	@Column(name = "is_under_development", columnDefinition = "boolean default false")
	private Boolean isUnderDevelopment = false;

	@Column(name = "is_active_for_mobile")
	private Boolean isActiveForMobile = false;

}