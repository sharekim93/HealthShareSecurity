package com.cafe24.healthshare.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		
		log.info("...............LoginSuccessHandler:Login Success");
		
		List<String> roleNames = new ArrayList<>();
		auth.getAuthorities().forEach(authority -> {roleNames.add(authority.getAuthority());});
		
		log.info("...............LoginSuccessHandler : ROLE NAMES :"+roleNames);
		String path="";
		if(roleNames.contains("ROLE_ADMIN")) {
			path="/security/admin";
		}
		if(roleNames.contains("ROLE_MEMBER")) {
			path = "/member/mypage";
		}
		
		response.sendRedirect(request.getContextPath()+path);
	}

}
