package com.forestory.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.forestory.dto.CustomUserDetails;

@Controller
public class ClientController {
	
	@GetMapping("/")
	public String index(@AuthenticationPrincipal CustomUserDetails userDetail ,Model model) {
		
		return "client/clientMain";
	}
	
	@GetMapping("/test")
	public String test(@AuthenticationPrincipal CustomUserDetails userDetail, Model model) {

		// 실험1
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("email",email);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();
		model.addAttribute("role",role);
		
		//실험2
		if(userDetail != null) {
			String email2 = userDetail.getUsername();
			model.addAttribute("email2", email2);

			String nickName = userDetail.getUserNick();
			model.addAttribute("nickName", nickName);
			
			String phoneNumber = userDetail.getUserPhone();
			model.addAttribute("phoneNumber", phoneNumber);
			
			String regdate = userDetail.getUserRegdate();
			model.addAttribute("regdate", regdate);
			
			
			//실험3
			model.addAttribute("userDetail", userDetail);
		}
		
		return "client/test";
	}

}
