package com.cafe24.healthshare.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.healthshare.dto.Board;
import com.cafe24.healthshare.dto.BoardSearch;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper mapper;
	
//	public int writePost(Board dto) ;
	
	public void writeTest() {
		Board dto = new Board();
		dto.setBtitle("테스트제목");
		dto.setUsername("a");
		dto.setBcontent("테스트입니다");
		dto.setBip("1234");
		dto.setBimg("이미지.jpg");
		
		log.info("WriteTest : "+ mapper.writePost(dto));
	}
//	public Board getPost(Board dto) ;
	public void getPostTest() {
		Board dto = new Board();
		dto.setBno(1);
		log.info("Get Post : "+mapper.getPost(dto));
	}
//	public List<Board> getList(String field, String query,int page) ;
	
	public void getListTest() {
		BoardSearch dto = new BoardSearch();
		dto.setField("btitle"); dto.setQuery("");dto.setPage(0);
		log.info("Get List : "+mapper.getList(dto));
	}
//	public int getPostCount(String field, String query) ;
	
	public void getPostCountTest() {
		BoardSearch dto = new BoardSearch();
		dto.setField("btitle"); dto.setQuery("");dto.setPage(0);
		log.info("GET List Count : "+mapper.getPostCount(dto));
	}
//	public int updatePost(Board dto) ;

	public void updatePost() {
		Board dto = new Board();
		dto.setBno(1);
		dto.setBtitle("제목수정");
		dto.setUsername("a");
		dto.setBcontent("내용수정");
		dto.setBip("1234");
		dto.setBimg("이미지.jpg");
		log.info("UpdatePostTEST : "+mapper.updatePost(dto));
	}
	
//	public int updateHit(Board dto) ;	
	
	public void updateHit() {
		Board dto = new Board();
		dto.setBno(1);
		log.info("updateHit : "+mapper.updateHit(dto));
	}
//	public int updateStep(Board dto) ;
	
	public void updateStepTest() {
		Board dto = new Board();
		dto.setBno(1);
		log.info("updateStep : "+mapper.updateStep(dto));
	}
//	public int replyPost(Board dto) ;
	
	public void replyPostTest() {
		Board dto = new Board();
		dto.setBno(1);
		dto.setBtitle("제목1수정");
		dto.setUsername("a");
		dto.setBcontent("내용수1정");
		dto.setBip("1234");
		dto.setBimg("이미지.jpg");
		log.info("replyPostTest : "+mapper.replyPost(dto));
	}
//	public int deletePost(Board dto) ;
	@Test
	public void deleteTest() {
		Board dto = new Board();
		dto.setBno(1); dto.setUsername("a");
		log.info("DeleteTEST : "+mapper.deletePost(dto));
	}
}
