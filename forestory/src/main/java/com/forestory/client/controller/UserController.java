package com.forestory.client.controller;



import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forestory.client.service.UserService;
import com.forestory.dto.UserDTO;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
	
	@Setter(onMethod_= @Autowired)
	private UserService userService;
	
	//회원가입 화면
	@GetMapping("/signUpPage")
	public String signUpForm(Model model) {
		model.addAttribute("userDto", new UserDTO());
		return "client/signUp/signUpPage";
	}
	
	//회원가입
	@PostMapping("/signUpProc")
	public String signUp(@Valid @ModelAttribute UserDTO userDto, BindingResult result, RedirectAttributes ras) {
		
		boolean duplicateCheck = userService.existsByUserEmail(userDto) || userService.existsByUserNick(userDto);
		
		// 유효성 검사 통과 못할 경우
		if(result.hasErrors() || duplicateCheck) {
			ras.addFlashAttribute("validErrors", userService.validateHandling(userDto, result));
			return "redirect:/auth/signUpPage";
		}
		
		userService.signUp(userDto);
		
		return "redirect:/auth/loginPage";
	}
	
	// 회원가입 이메일 중복 검사
	@ResponseBody
	@PostMapping(value="/userEmailVaild", produces = "application/json; charset=UTF-8")
	public boolean userEmailVaild(@RequestBody UserDTO userDto) {
		return userService.existsByUserEmail(userDto); // email 중복일 경우 true 반환
	}
	
	// 회원가입 닉네임 중복 검사
	@ResponseBody
	@PostMapping(value="/userNickVaild", produces = "application/json; charset=UTF-8")
	public boolean userNickVaild(@RequestBody UserDTO userDto) {
		return userService.existsByUserNick(userDto); // nick 중복일 경우 true 반환
	}
	
	//로그인 화면
	@GetMapping("/loginPage")
	public String loginForm(@RequestParam(required = false) boolean hasError, 
			@RequestParam(required = false) String errorMessage, Model model) {
		// 로그인 실패
		model.addAttribute("hasError", hasError);
		model.addAttribute("errorMessage", errorMessage);
		return "client/login/loginPage";
	}
	
	//권한 없는 접근
	@GetMapping("/authErrors")
	public String noAuthPage(@RequestParam String authFail, Model model) {
		model.addAttribute("authFail", authFail);
		return "client/login/authErrors";
	}
}