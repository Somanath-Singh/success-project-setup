package com.aashdit.prod.heads.hims.umt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.aashdit.prod.heads.hims.umt.utils.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="t_umt_role_entity_map", schema = "public")
public class RoleEntityMap extends Auditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4225796378784615709L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_entity_map_id")
	private Long roleEntityMapId;
	
	@Column(name = "role_code")
	private String roleCode;
	
	@Column(name = "entity_id")
	private Long entityId;

	@Column(name = "entity_level")
	private String entityLevel;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_public_role_needed", columnDefinition = "boolean default false")
	private Boolean isPublicRoleNeeded = false;

}
