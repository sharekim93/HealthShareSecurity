package com.cafe24.healthshare.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.healthshare.dto.Board;
import com.cafe24.healthshare.dto.BoardSearch;
import com.cafe24.healthshare.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	/*
	 * 메서드명	: writePost
	 * 파라미터	: Board dto, MultipartFile file, HttpServletRequest request
	 * 내  용	 	: 위 파라미터에서 dto에 ip와 fileName을 설정하고 설정한 DTO를 mapper로 전달합니다.
	 * return	: int(1 or 0 )
	 */
	@Override 
	public int writePost(Board dto,MultipartFile file,HttpServletRequest request) { 
		try { dto.setBip(InetAddress.getLocalHost().getHostAddress()); } catch (UnknownHostException e) { e.printStackTrace(); }
		try { dto.setBfile(fileUpload(file.getOriginalFilename(),request,file.getBytes())); } catch (IOException e) { e.printStackTrace(); }
		return mapper.writePost(dto);
	}
	
	
	/*
	 * 메서드명	: getPost
	 * 파라미터	: Board dto
	 * 내  용		: 전달 받은 Board dto로 조회수를 증가시키고(updateHit), 해당하는 글의 정보를 dto의 형태로 가져옵니다(getPost)
	 * return	: Board
	 */
	@Override public Board getPost(Board dto) {
			   mapper.updateHit(dto);
		return mapper.getPost(dto);
	}
	
	
	/*
	 * 메서드명	: getList
	 * 파라미터	: 없음
	 * 내  용		: BoardSearch dto를 기본값(field="btitle",query="",page=0)으로 설정하고 10개의 보드를 List로 가져옵니다.
	 * return	: List<Board>
	 */
	@Override
	public List<Board> getList() {
		BoardSearch dto = new BoardSearch("btitle","",0);
		return mapper.getList(dto);
	}
	
	
	/*
	 * 메서드명	: getList
	 * 파라미터	: BoardSearch dto
	 * 내  용		: 전달받은 dto에 해당하는 10개의 post를 List로 가져옵니다.
	 * return	: List<Board>
	 */
	@Override public List<Board> getList(BoardSearch dto) { return mapper.getList(dto); }
	
	
	/*
	 * 메서드명	: getPostCount
	 * 파라미터	: BoardSearch dto
	 * 내  용		: 전달받은 dto에 해당하는 post의 수를 return 합니다
	 * return	: int
	 */
	@Override public int getPostCount(BoardSearch dto) { return mapper.getPostCount(dto); }
	
	
	/*
	 * 메서드명	: updatePost
	 * 파라미터	: Board dto,MultipartFile file, HttpServletRequest request
	 * 내  용		: Update.jsp페이지에서 작성한 내용을 Board로 전달 받아 수정합니다. MultipartFile이 전달이 된 경우 파일 이름을 바꿉니다.
	 * return	: int
	 */
	@Override
	public int updatePost(Board dto,MultipartFile file, HttpServletRequest request) {
		dto.setBfile(request.getParameter("prev_file"));
		
		if(file.getOriginalFilename().length()!=0) {
			try {dto.setBfile(fileUpload(file.getOriginalFilename(),request,file.getBytes()));} 
			catch (IOException e) {e.printStackTrace(); TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();}
		}
		int result = mapper.updatePost(dto);
		return result;
	}

	/*
	 * 메서드명	: replyPost
	 * 파라미터	: Board dto,MultipartFile file, HttpServletRequest request
	 * 내  용		: writePost와 마찬가지로 전달받은 dto,file,request 정보로 답글을 남깁니다.
	 * return	: int
	 */
	@Override public int replyPost(Board dto,MultipartFile file, HttpServletRequest request) {

		try { dto.setBip(InetAddress.getLocalHost().getHostAddress()); } catch (UnknownHostException e) { e.printStackTrace(); }
		try { dto.setBfile(fileUpload(file.getOriginalFilename(),request,file.getBytes())); } catch (IOException e) { e.printStackTrace(); }
		int result = mapper.replyPost(dto); return result; 
	}
	
	/*
	 * 메서드명	: deletePost
	 * 파라미터	: Board dto
	 * 내  용		: 전달 받은 dto의 bno와 username을 비교하여 글을 삭제합니다.
	 * return	: int
	 */
	@Override public int deletePost(Board dto) {  int result = mapper.deletePost(dto); return result; }
	
	/*
	 * 메서드명	: fileUpload
	 * 파라미터	: String originName,HttpServletRequest request, byte[] fileData
	 * 내  용		: originName과 fileData를 전달 받아 path에 저장하고 저장된 파일 이름을 return 합니다.
	 * return	: String
	 */
	public String fileUpload(String originName,HttpServletRequest request, byte[] fileData) throws IOException{
		String saveName = UUID.randomUUID().toString() + "_" + originName;
		//String path="/upload";
		String path=request.getSession().getServletContext().getRealPath("/")+"upload";
		
		File target = new File(path,saveName);
		FileCopyUtils.copy(fileData, target);
		return saveName;
	}

}
