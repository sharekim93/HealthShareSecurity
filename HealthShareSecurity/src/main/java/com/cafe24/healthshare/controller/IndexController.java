package com.cafe24.healthshare.controller;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@EnableAspectJAutoProxy
public class IndexController {
	
	@GetMapping("/introduce")
	public void introduce() {}

}
