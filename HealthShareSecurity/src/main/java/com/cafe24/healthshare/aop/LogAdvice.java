package com.cafe24.healthshare.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cafe24.healthshare.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAdvice {

	@Autowired
	UserService service;
	
	/*
	 * AOP 이름	: controllerLogging
	 * 방식	  	: @Before
	 * Exception: Throwable
	 * 내용 		: 컨트롤러 이동 시 Method(GET,POST)와 ServletPath를 표시한다
	 */
	@Before("execution (* com.cafe24.healthshare.controller.*Controller.*(..))")
	public void controllerLogging() throws Throwable{
		HttpServletRequest request = 
		        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		log.info(request.getMethod()+" : "+request.getServletPath());
	}
	
	/*
	 * AOP 이름	: ServiceLogging
	 * 방식	  	: @Before
	 * Exception: Throwable
	 * 내용 		: 서비스 메서드 실행 시 메서드명을 로깅한다. 서비스에서는 결과를 로깅한다
	 */
	@Around("execution (* com.cafe24.healthshare.service.*Service*.*(..))")
	public Object serviceLogging(ProceedingJoinPoint pjp) throws Throwable {
		log.info(pjp.getSignature().toShortString());
		return pjp.proceed();
	}
}
