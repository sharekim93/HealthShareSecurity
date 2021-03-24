package com.cafe24.healthshare.mapper;

import java.util.List;

import com.cafe24.healthshare.dto.Board;
import com.cafe24.healthshare.dto.BoardSearch;

public interface BoardMapper {
	public int writePost(Board dto) ;
	public Board getPost(Board dto) ;
	public List<Board> getList(BoardSearch dto) ;
	public int getPostCount(BoardSearch dto) ;
	public int updatePost(Board dto) ;
	public int updateHit(Board dto) ;	
	public int updateStep(Board dto) ;
	public int replyPost(Board dto) ;
	public int deletePost(Board dto) ;
}
