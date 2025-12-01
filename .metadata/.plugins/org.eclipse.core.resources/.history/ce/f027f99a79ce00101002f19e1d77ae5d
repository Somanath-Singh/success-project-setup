package com.aashdit.prod.heads.hims.ipms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "t_guest_faculty_applicant_reg")
public class RegistrationForm implements Serializable{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -7363636242831791089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reg_id")
	private Long registrationId;
	
	@Column(name = "reg_code")
	private String registrationCode;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "status")
	private String status;
	
	 @ManyToOne
	 @JoinColumn(name = "gender_id", nullable = false)
	 private MasterType genderId;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "mobile_no")
	private String contact;

	@Column(name = "email")
	private String email;

	@Column(name = "is_Active")
	private Boolean isActive;

}
