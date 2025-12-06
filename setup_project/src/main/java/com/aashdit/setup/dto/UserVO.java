package com.aashdit.setup.dto;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class UserVO {

	private Long userId;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;

	private RoleDto primaryRole;

	private String mobile;

	private String email;

	private String userType;// STAFF OR STUDENT

	private String userLevel;// COLG or UNV or DEPT

	private Long userTypeId;// staff or student id

	private Set<GrantedAuthority> grantedAuthorities;

	private Long entityId;// college id or department id or university id

	private List<MenuDto> menuList;

	private Long staffDepartmentId;

	private String ssoUrl;

	private String jwtForCore;

	private String beneficiaryCode;

	private String designation;
}
