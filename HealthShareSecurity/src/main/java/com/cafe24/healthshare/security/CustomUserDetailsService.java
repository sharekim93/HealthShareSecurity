package com.cafe24.healthshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cafe24.healthshare.mapper.UserMapper;
import com.cafe24.healthshare.vo.User;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = mapper.authenticate(username);
		if(user == null) {throw new UsernameNotFoundException(username);}
		return user;
	}

}
