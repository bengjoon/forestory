package com.forestory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	// admin 메인
	@GetMapping("/admin")
	public String adminMain() {
		return "admin/adminMain";
	}
	
}
