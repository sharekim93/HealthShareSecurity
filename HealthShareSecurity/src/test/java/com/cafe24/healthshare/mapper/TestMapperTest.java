package com.cafe24.healthshare.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class TestMapperTest {
	
	@Setter(onMethod_=@Autowired)
	private TestMapper mapper;
	
	@Test
	public void mapperTest() {
		
		log.info("......................Mapper Test..............."+mapper.getTime());
	}
}
