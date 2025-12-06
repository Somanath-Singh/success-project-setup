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
@Table(name="t_umt_org_structure", schema = "public")
public class OrganizationStructure extends Auditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4225796378784615689L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "object_id")
	private Long objectId;

	@Column(name = "object_type")
	private String objectType;

}
