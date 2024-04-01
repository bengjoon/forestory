package com.forestory.custom;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
//		String uri = request.getRequestURI();
		String authFail = "권한이 없는 접근입니다.";
		
		authFail = URLEncoder.encode(authFail, "UTF-8");
		
		response.sendRedirect("/auth/authErrors?authFail="+authFail);
		
	}

}
