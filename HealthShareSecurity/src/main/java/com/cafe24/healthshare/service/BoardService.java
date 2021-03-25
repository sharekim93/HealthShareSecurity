package com.cafe24.healthshare.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cafe24.healthshare.dto.Board;
import com.cafe24.healthshare.dto.BoardSearch;

public interface BoardService {
	public int writePost(Board dto,MultipartFile file,HttpServletRequest request) ;
	public Board getPost(Board dto) ;
	public List<Board> getList();
	public List<Board> getList(BoardSearch dto) ;
	public int getPostCount(BoardSearch dto);
	public int updatePost(Board dto,MultipartFile file, HttpServletRequest request) ;
	public int replyPost(Board dto,MultipartFile file, HttpServletRequest request) ;
	public int deletePost(Board dto) ;
}
