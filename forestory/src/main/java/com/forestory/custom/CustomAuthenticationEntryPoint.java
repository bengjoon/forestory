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
		} else {
			authFail = "존재하지 않는 페이지거나 로그인해주세요.";
		} 
		
		authFail = URLEncoder.encode(authFail, "UTF-8");
		
		response.sendRedirect("/auth/authErrors?authFail="+authFail);
		
	}

}
