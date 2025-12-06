package com.aashdit.setup.umt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.aashdit.setup.umt.utils.Auditable;

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
@Table(name = "t_umt_user_login_history", schema = "public")
public class UserLoginHistory extends Auditable<Long> implements Serializable{

	private static final long serialVersionUID = 3649339921550049779L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_login_history_id")
	private Long userLoginHistoryId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;
	
	@Column(name = "ip_address")
	private String ipAddress;
	
	@Column(name = "logged_in_date")
	private Date loggedInDate;
	
	@Column(name = "logged_out_date")
	private Date loggedOutDate;
	
	@Column(name = "browser_details")
	private String browserDetails;
	
	@Column(name = "os_details")
	private String osDetails;
	
	@Column(name = "login_type")
	private String loginType;
	
	@Column(name = "login_status")
	private String loginStatus;

}
