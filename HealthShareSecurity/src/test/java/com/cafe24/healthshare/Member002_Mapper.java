package com.cafe24.healthshare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.healthshare.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@Slf4j
public class Member002_Mapper {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

 
	@Test
	public void tetGetTime2() { 
		log.info(mapper.read_security("admin").toString());
	}
}
