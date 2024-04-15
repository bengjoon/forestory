package com.forestory.client.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forestory.dto.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MyPageController {
	
	@GetMapping("/myInfo")
	public String myPage(@AuthenticationPrincipal CustomUserDetails userDetail, Model model ) {
		if(userDetail != null) {
			model.addAttribute("userDetail", userDetail);
		}
		return "/client/myPage/myInfo";
	}

}
