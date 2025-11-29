package com.aashdit.prod.heads.common.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_mst_district")
public class District implements Serializable {

	private static final long serialVersionUID = 8185420005548101716L;

	@Id
	@Column(name = "district_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long districtId;
	
	@NotNull
	@Column(name = "district_name")
	private String districtName;

	@Column(name = "district_name_hi")
	private String districtNameHi;
	
	@NotNull
	@Column(name = "district_code")
	private String districtCode;

	@Column(name = "is_active")
	private Boolean isActive;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State stateId;
	
}
	