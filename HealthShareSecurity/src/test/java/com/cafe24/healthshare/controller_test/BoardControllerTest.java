package com.cafe24.healthshare.controller_test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.healthshare.controller.BoardController;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Slf4j
public class BoardControllerTest {

	private WebApplicationContext context;
	private MockMvc mock;
	
	@Autowired
	private BoardController controller;
	
	@Before
	public void setup() { this.mock = MockMvcBuilders.standaloneSetup(controller).build(); }
	
	@Test
	public void writePosttest() throws Exception{
		MockMultipartFile file 
		= new MockMultipartFile("bimg","001.jpg",MediaType.TEXT_PLAIN_VALUE,"001.jpg".getBytes());
		
		MockHttpServletRequestBuilder builder =
				MockMvcRequestBuilders.fileUpload("/board/write")
				.file(file).param("bname","2").param("username","a").param("btitle","제목").param("bcontent","내용").param("bip","2");
		
		log.info(mock.perform(builder).andReturn().getModelAndView().getModelMap().toString());
	}
}
