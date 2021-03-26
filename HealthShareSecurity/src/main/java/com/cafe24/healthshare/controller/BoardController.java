package com.cafe24.healthshare.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.healthshare.dto.Board;
import com.cafe24.healthshare.dto.BoardSearch;
import com.cafe24.healthshare.service.BoardService;
import com.cafe24.healthshare.util.Paging;

@Controller
@RequestMapping("/board/")
@EnableAspectJAutoProxy
public class BoardController {

	@Autowired
	private BoardService service;
	
	//GET
	@GetMapping("list") 
	public void list( @RequestParam(value="p", defaultValue="0") int pstartno,Model model) {
		BoardSearch dto = new BoardSearch(pstartno);
		model.addAttribute("list",service.getList(dto));
		model.addAttribute("paging",new Paging(pstartno, service.getPostCount(dto)));
	}
	@GetMapping("write") public void goWrite() {}
	@GetMapping("detail") public void detail(Board dto,Model m) { m.addAttribute(service.getPost(dto)); }
	@GetMapping("update") public void goUpdate(Board dto,Model m) { m.addAttribute(service.getPost(dto)); }
	@GetMapping("reply") public void goReply(Board dto,Model m) { m.addAttribute(service.getPost(dto)); }
	
	//POST
	@PostMapping("write")
	public String write(Board dto,MultipartFile file,HttpServletRequest request,RedirectAttributes rttr) {
		String result ="";
		if(service.writePost(dto,file,request)>0) {result="글쓰기에 성공했습니다.";
		rttr.addFlashAttribute("success",result);}
		return "redirect:/board/list";
	}
	@PostMapping("update")
	public String updatePost(Board dto,MultipartFile file,HttpServletRequest request,RedirectAttributes rttr) {
		String result ="";
		if(service.updatePost(dto,file,request)>0) {
			result="수정에 성공했습니다.";
			rttr.addFlashAttribute("success",result);
			rttr.addAttribute(service.getPost(dto));
		}
		return "redirect:/board/detail";
	}
	@PostMapping("delete")
	public String deletePost(Board dto,RedirectAttributes rttr) {
		String result ="";
		if(service.deletePost(dto)>0) {result="삭제에 성공했습니다.";
		rttr.addFlashAttribute("success",result);}
		return "redirect:/board/list";
	}
	@PostMapping("reply")
	public String replyPost(Board dto,MultipartFile file, HttpServletRequest request,RedirectAttributes rttr) {
		String result ="";
		if(service.replyPost(dto,file,request)>0) {result="답글 작성에 성공했습니다.";
		rttr.addFlashAttribute("success",result);}
		return "redirect:/board/list";
	}
}