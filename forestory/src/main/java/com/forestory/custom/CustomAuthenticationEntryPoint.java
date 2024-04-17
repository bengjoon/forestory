package com.forestory.custom;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		String uri = request.getRequestURI();
		String authFail;
		
		if(uri.equals("/insert")) {
			authFail = "로그인 후 글쓰기 가능합니다.";
		} else if(uri.equals("/edit")) {
			authFail = "로그인 후 수정 가능합니다.";
		} else {
			authFail = "권한이 없는 페이지입니다.";
		} 
		
		authFail = URLEncoder.encode(authFail, "UTF-8");
		
		response.sendRedirect("/auth/authErrors?authFail="+authFail);
		
	}

}
