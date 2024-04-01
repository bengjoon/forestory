package com.forestory.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.forestory.dto.CustomUserDetails;

@Controller
public class AdminController {

	// admin 메인
	@GetMapping("/admin")
	public String adminMain(@AuthenticationPrincipal CustomUserDetails userDetail) {
		
		return "admin/adminMain";
	}
	
}
