package com.forestory.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.forestory.dto.CustomUserDetails;

@Controller
public class AdminController {

	// admin 메인
	@GetMapping("/admin")
	public String adminMain(@AuthenticationPrincipal CustomUserDetails userDetail) {
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
//		GrantedAuthority auth = iter.next();
//		
//		String role = auth.getAuthority();
//		
//		if(role != "ROLE_ADMIN") {
//			return "redirect:/";
//		}
		
		return "admin/adminMain";
	}
	
}
