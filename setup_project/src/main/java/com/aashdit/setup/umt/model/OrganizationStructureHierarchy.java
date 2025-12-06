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
@Table(name="t_umt_org_structure_hierarchy", schema = "public")
public class OrganizationStructureHierarchy extends Auditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4225796378784615689L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "level_code", nullable = false)
	private String levelCode;

	@Column(name = "level_height")
	private Integer levelHeight;

	@Column(name = "parent_hierarchy_id")
	private Long parentHierarchyId;

	@ManyToOne
	@JoinColumn(name = "org_structure_id")
	private OrganizationStructure organizationStructure;


}
