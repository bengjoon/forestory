package com.forestory.client.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forestory.domain.Board;
import com.forestory.domain.User;
import com.forestory.dto.CustomUserDetails;
import com.forestory.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyPageController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/info")
	public String myInfo(@AuthenticationPrincipal CustomUserDetails userDetail, Model model) {
		if(userDetail != null) {
			model.addAttribute("userDetail", userDetail);
		}
		return "/client/myPage/myInfo";
	}
	
	@GetMapping("/board")
	public String myBoard(@AuthenticationPrincipal CustomUserDetails userDetail, Model model) {
		
		User user = userRepository.findByUserNo(userDetail.getUserNo());
		
		List<Board> myBoard = user.getBoards();
		Collections.reverse(myBoard);
		
		model.addAttribute("myBoard", myBoard);
		
		return "/client/myPage/myBoard";
	}
	

}
