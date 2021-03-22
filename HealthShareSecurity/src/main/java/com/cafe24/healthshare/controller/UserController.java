package com.cafe24.healthshare.controller;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.healthshare.dto.Join;
import com.cafe24.healthshare.dto.UpdateInfo;
import com.cafe24.healthshare.dto.UpdatePass;
import com.cafe24.healthshare.service.UserService;
import com.cafe24.healthshare.vo.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/")
@EnableAspectJAutoProxy
@Slf4j
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("accessError")
	public String accessError(AuthenticationSuccessHandler auth,Model model) {
		model.addAttribute("msg","AccessDemone"); return "inc/accessError";
	}
	
	@GetMapping("login") public String login(Authentication auth) {
		if(auth!=null) {return "redirect:/member/mypage";}
		return "/member/login";
	}
	
	@GetMapping("loginFail")
	public String loginFail(RedirectAttributes rttr) {
		String result = "아이디와 비밀번호를 확인하세요";
		rttr.addFlashAttribute("result",result);
		return "redirect:/member/login";
	}

	@GetMapping("KakaoLogin")
	public String kakaoLogin(@RequestParam String code,RedirectAttributes rttr,Model model) {
		String username = service.getUsernameFromKakao(code);
		if(service.validateUser(username)==0) {
				rttr.addFlashAttribute("result","카카오로 가입되지 않은 아이디입니다. 가입 후 이용하세요");
				rttr.addFlashAttribute("kakaoid",username);
				return "redirect:/member/join";
		}
		return "/member/login";
	}
	
	@GetMapping("joinAgree") public void joinAgree() {}	
	@GetMapping("join") public void join() {}
	
	@GetMapping("idCheck")
	@ResponseBody
	public String idCheck(@RequestParam String userid) {
		return String.valueOf(service.validateUser(userid)); }
	
	@PostMapping("join")
	public String joinAction(Join join,Model model){
		service.joinUser(join);
		model.addAttribute("info",service.getUserInfo(join.getUsername()));
		return "/member/join_com";
	}
	
	@GetMapping("mypage")
	public void goMypage(Authentication auth,Model model) {
		model.addAttribute("user",service.getUserInfo(auth.getName())); }

	@GetMapping("editInfo")
	public void editInfo(Authentication auth,Model model) {
		model.addAttribute("user",service.getUserInfo(auth.getName()));
	}

	@PostMapping("editInfo")
	public String editAction(UpdateInfo info,Model model) {;
		service.updateUserInfo(info);
		model.addAttribute("user",service.getUserInfo(info.getUsername()));
		return "/member/mypage";
	}
	
	@GetMapping("editPass") public void editPass() {}
	
	@PostMapping("editPass")
	public String editPassAction(UpdatePass info,Model model) {
		service.updateUserPass(info);
		model.addAttribute("user",service.getUserInfo(info.getUsername()));
		return "/member/mypage";
	}
	
	@GetMapping("delete")
	public void delete() {}
	
	@PostMapping("delete")
	public String deleteAction(User user) { service.deleteUser(user); return "/member/login"; }
	
}
