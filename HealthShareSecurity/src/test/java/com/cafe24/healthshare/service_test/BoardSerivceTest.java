package com.cafe24.healthshare.service_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.healthshare.dto.Board;
import com.cafe24.healthshare.dto.BoardSearch;
import com.cafe24.healthshare.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class BoardSerivceTest {

	@Autowired
	private BoardService service;
	

	public void writeTest() {
		Board dto = new Board();
		dto.setBtitle("테스트제목");
		dto.setUsername("a");
		dto.setBcontent("테스트입니다");
		dto.setBip("1234");
		dto.setBfile("이미지.jpg");
		
		
	}
	
	
	public void getPostTest() {
		Board dto = new Board();
		dto.setBno(2);
		log.info(service.getPost(dto).toString());
	}
	
	public void getListTest() {
		BoardSearch dto = new BoardSearch();
		dto.setField("btitle"); dto.setQuery(""); dto.setPage(0);
		log.info(service.getList(dto).toString());
	}
	
	
	public void updateTest() {
		Board dto = new Board();
		dto.setBno(2);
		dto.setBtitle("수정해따");
		dto.setUsername("a");
		dto.setBcontent("수정테스트입니다");
		dto.setBip("1234");
		dto.setBfile("이미지.jpg");
		
//		service.updatePost(dto);
	}
	
	
	public void replyTest() {
		Board dto = new Board();
		dto.setBno(3);
		dto.setBtitle("댓글이요");
		dto.setUsername("a");
		dto.setBcontent("답글내용입니다");
		dto.setBip("1313");
		
//		service.replyPost(dto);
	}
	
	@Test
	public void deleteTest() {
		Board dto = new Board();
		dto.setBno(10);
		dto.setUsername("a");
		
		service.deletePost(dto);
	}
}
