package com.aashdit.prod.heads.hims.umt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aashdit.prod.heads.hims.umt.model.LoggedInUser;
import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.repository.UserRepository;

@Component(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user != null) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority(user.getPrimaryRole().getRoleCode()));

			return new LoggedInUser(username, user.getPassword(), true, true, true, true, grantedAuthorities,
					user.getPrimaryRole(), user);

		}
		throw new UsernameNotFoundException("User not found with username: " + username);
	}

}
