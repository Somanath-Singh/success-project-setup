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
@Table(name="t_umt_super_entity", schema = "public")
public class SuperEntity extends Auditable<Long> implements Serializable {
	private static final long serialVersionUID = 285701719160134651L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "super_entity_id")
	private Long superEntityId;

	@Column(name = "super_entity_name")
	private String superEntityName;

	@Column(name = "super_entity_code")
	private String superEntityCode;

	@Column(name = "pre_data_json")
	private String preDataJson;

	
} 
  