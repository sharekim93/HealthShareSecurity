package com.cafe24.healthshare.controller;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.healthshare.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("accessError")
	public String accessError(AuthenticationSuccessHandler auth,Model model) { model.addAttribute("msg","AccessDemone"); return "security/accessError"; }
	
	@GetMapping("login")
	public void login() {log.info("...........login.........");}	
	
	@GetMapping("logout")
	public String logout() {log.info("..........logout......."); return "member/login";}	
	
	@GetMapping("joinAgree")
	public void joinAgree() {log.info(".....Go Join Agree Page......");}
	
	@GetMapping("join")
	public void join() {log.info(".....Go Join Page.....");}
	
	@GetMapping("idCheck")
	@ResponseBody
	public String idCheck(@RequestParam String userid) {
		log.info(".......check id duplicated....");
		return String.valueOf(service.validateUser(userid));
	}
	
	@PostMapping("join")
	@ResponseBody
	public void joinAction() {
		log.info(".....Join Action.....");
		
	}
	
	@GetMapping("mypage")
	public void goMypage() {log.info("......Go mypage......");}

	@GetMapping("editInfo")
	public void editInfo() {log.info("......Go Info Edit page.....");}

	@Update("editInfo")
	public void editAction() {log.info("....Edit Info Action.....");}
	
	@GetMapping("editPass")
	public void editPass() {log.info(".....Go Edit Pass page.....");}
	
	@Update("editPass")
	public void editPassAction() {log.info(".....Edit Pass Action.....");}
	
	@GetMapping("delete")
	public void delete() {log.info(".....Go Delete Page.....");}
	
	@Delete("delete")
	public void deleteAction() {log.info(".....Delete Action.....");}
	
}
