package com.aashdit.prod.heads.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.aashdit.prod.heads.framework.core.annotation.Sortable;

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
@Table(name = "t_mst_gender", schema = "public")
public class Gender implements Serializable {

	private static final long serialVersionUID = 832436664155432699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gender_id")
	private Long genderId;

	@Column(name = "gender_code")
	private String genderCode;

	@Column(name = "gender_name_en")
	@Sortable(lang = "en")
	private String genderNameEN;

	@Column(name = "gender_name_hi")
	@Sortable(lang = "hi")
	private String genderNameHI;

	@Column(name = "is_active")
	private Boolean isActive;


}
