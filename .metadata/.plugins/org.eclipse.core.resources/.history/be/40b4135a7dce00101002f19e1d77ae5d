package com.aashdit.prod.heads.hims.ipms.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.aashdit.prod.heads.hims.ipms.dto.RoleDto;
import com.aashdit.prod.heads.hims.ipms.dto.UserVO;



public class LoggedInUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 7767108758059803455L;

	private RoleDto primaryRole;

	private UserVO dbUser;

	public LoggedInUser(String username, String password, boolean enabled, boolean accountNonExpired,
				boolean credentialsNonExpired, boolean accountNonLocked,
				Collection<? extends GrantedAuthority> authorities,  RoleDto primaryRole, UserVO dbUser)
    {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		
		this.primaryRole = primaryRole;
		//this.userId = userId;
		this.dbUser = dbUser;
		

	}

	public RoleDto getPrimaryRole() {
		return primaryRole;
	}
	
	public UserVO getDbUser()
	{
		return dbUser;
	}
}
