package com.cafe24.healthshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cafe24.healthshare.dto.User;
import com.cafe24.healthshare.mapper.MemberMapper;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = mapper.read_security(username);
		if(user == null) {throw new UsernameNotFoundException(username);}
		return user;
	}

}
