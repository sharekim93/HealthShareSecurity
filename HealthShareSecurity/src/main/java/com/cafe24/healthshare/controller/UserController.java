package com.cafe24.healthshare.controller;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.healthshare.dto.Join;
import com.cafe24.healthshare.dto.UpdateInfo;
import com.cafe24.healthshare.dto.UpdatePass;
import com.cafe24.healthshare.service.UserService;
import com.cafe24.healthshare.vo.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("accessError")
	public String accessError(AuthenticationSuccessHandler auth,Model model) {
		model.addAttribute("msg","AccessDemone"); return "security/accessError";
	}
	
	@GetMapping("login") public void login() {log.info("...........login.........");}	
	@GetMapping("joinAgree") public void joinAgree() {log.info(".....Go Join Agree Page......");}	
	@GetMapping("join") public void join() {log.info(".....Go Join Page.....");}
	
	@GetMapping("idCheck")
	@ResponseBody
	public String idCheck(@RequestParam String userid) {
		log.info(".......check id duplicated....");
		return String.valueOf(service.validateUser(userid));
	}
	
	@PostMapping("join")
	public String joinAction(Join join,Model model) throws UnknownHostException {
		log.info(".....Join Action.....");
		service.joinUser(join);
		model.addAttribute("info",service.getUserInfo(join.getUsername()));
		return "/member/join_com";
	}
	
	@GetMapping("mypage")
	public void goMypage(Authentication auth,Model model) {
		log.info("......Go mypage......");
		model.addAttribute("user",service.getUserInfo(auth.getName()));
	}

	@GetMapping("editInfo")
	public void editInfo(Authentication auth,Model model) {
		log.info("......Go Info Edit page.....");
		model.addAttribute("user",service.getUserInfo(auth.getName()));
	}

	@PostMapping("editInfo")
	public String editAction(UpdateInfo info,Model model) {
		log.info("....Edit Info Action.....");
		log.info(info.toString());
		service.updateUserInfo(info);
		model.addAttribute("user",service.getUserInfo(info.getUsername()));
		return "/member/mypage";
	}
	
	@GetMapping("editPass") public void editPass() {log.info("Go Edit Pass page");}
	
	@PostMapping("editPass")
	public String editPassAction(UpdatePass info,Model model) {
		log.info("Edit Pass Action");
		service.updateUserPass(info);
		model.addAttribute("user",service.getUserInfo(info.getUsername()));
		return "/member/mypage";
	}
	
	@GetMapping("delete")
	public void delete() {log.info(".....Go Delete Page.....");}
	
	@PostMapping("delete")
	public String deleteAction(User user) {
		log.info(".....Delete Action.....");
		service.deleteUser(user);
		return "/member/login";
	}
	
}
