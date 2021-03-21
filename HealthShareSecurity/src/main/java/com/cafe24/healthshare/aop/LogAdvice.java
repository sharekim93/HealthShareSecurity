package com.cafe24.healthshare.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cafe24.healthshare.service.UserService;
import com.cafe24.healthshare.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAdvice {

	@Autowired
	UserService service;
	
	@Before("execution (* com.cafe24.healthshare.controller.*Controller.*(..))")
	public void doLogging() throws Throwable{
		HttpServletRequest request = 
		        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		log.info(request.getMethod()+" : "+request.getServletPath());
	}
	
}
