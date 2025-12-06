package com.aashdit.setup.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.aashdit.setup.core.annotation.Sortable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
@Entity
@Table(name = "t_mst_gramapanchayat")
public class Grampanchayat implements Serializable {

	private static final long serialVersionUID = 3736657245464159277L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gp_id")
	private Long gpId;

	@Column(name = "gp_code")
	private String gpCode;

	@Column(name = "gp_name_en")
	@Sortable(lang = "en")
	private String gpNameEN;

	@Column(name = "gp_name_hi")
	@Sortable(lang = "hi")
	private String gpNameHI;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "block_id")
	private Block block;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "gp_lgd_code")
	@Sortable(lang = "lgd")
	private String gpLgdCode;

	@Column(name = "gp_census_code")
	@Sortable(lang = "census")
	private String gpCensusCode;
	
	@Column(name = "gp_tribal")
	private Boolean gpTribal;

}
