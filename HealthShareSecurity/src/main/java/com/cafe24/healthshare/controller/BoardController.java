package com.cafe24.healthshare.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.healthshare.dto.Board;
import com.cafe24.healthshare.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private BoardService service;
	
	//GET
	@GetMapping("list") public void getList() {service.getList();}
	@GetMapping("write") public void goWrite() {}
	@GetMapping("detail") 
	public void detail(Board dto,Model m) {
		m.addAttribute("result",dto=service.getPost(dto));
	}
	@GetMapping("update")
	public void goUpdate(Board dto,Model m) {
		m.addAttribute("result",dto=service.getPost(dto));
	}
	@GetMapping("reply")
	public void goReply(Board dto,Model m) {
		m.addAttribute("result",dto=service.getPost(dto));
	}
	
	//POST
	@PostMapping("write")
	public String write(Board dto,MultipartFile file,HttpServletRequest request,RedirectAttributes rttr) {
		String result ="";
		if(service.writePost(dto,file,request)>0) {result="글쓰기에 성공했습니다.";
		rttr.addFlashAttribute("success",result);}
		return "redirect:/board/list";
	}
	@PostMapping("update")
	public String updatePost(Board dto,MultipartFile file,HttpServletRequest request,Model m) {
		service.updatePost(dto,file,request); m.addAttribute("result",dto = service.getPost(dto));
		return "/board/detail";
	}
	@PostMapping("delete")
	public String deletePost(Board dto) {
		service.deletePost(dto); 
		return "redirect:/board/list";
	}
	@PostMapping("reply")
	public String replyPost(Board dto,MultipartFile file, HttpServletRequest request,RedirectAttributes rttr) {
		service.replyPost(dto,file,request);
		return "redirect:/board/list";
	}
}
