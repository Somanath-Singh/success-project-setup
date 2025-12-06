package com.aashdit.setup.umt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aashdit.setup.umt.dto.CurrentUserVo;
import com.aashdit.setup.umt.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_umt_user")
public class User extends Auditable<Long> implements Serializable {
	private static final long serialVersionUID = 285701719160134651L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "dob")
	private Date dateOfBirth;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	@Column(name = "enabled")
	private Boolean isEnabled;

	@Column(name = "designation")
	private String designation;

	@Column(name = "allow_multiple_session")
	private Boolean allowMultipleSession;

	@Column(name = "is_locked")
	private Boolean isLocked;

	@Column(name = "is_logged_in")
	private Boolean isLoggedIn;

	@Column(name = "wrong_login_cnt")
	private Integer wrongLoginCount;

	@ManyToOne
	@JoinColumn(name = "primary_role_id")
	private Role primaryRole;

	@Column(name = "profile_photo")
	private String profilePhoto;

	//extra properties for user in key value pair
	@Column(name = "extra_properties")
	private String extraProperties;

	@Transient
	private List<Role> roles;

	@Column(name = "last_request_time")
	private Date lastRequestTime;

	@Column(name = "beneficiary_code")
	private String beneficiaryCode;
	
	@Column(name = "user_type")
	private String userType;

	@Column(name = "user_level")
	private String userLevel;

	@Column(name = "user_type_id")
	private Long userTypeId;

	@Transient
	private CurrentUserVo currentUserVo;
	
}
