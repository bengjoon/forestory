package com.forestory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forestory.admin.service.DashboardService;
import com.forestory.dto.CustomUserDetails;
import com.forestory.dto.GoogleChartVO;


@Controller
public class AdminController {
	
	@Autowired
	DashboardService dashBoardService;
	
	// admin 메인
	@GetMapping("/admin")
	public String adminMain(@AuthenticationPrincipal CustomUserDetails userDetail, Model model) {
		
		List<GoogleChartVO> googleChart = dashBoardService.findUserCount();
		model.addAttribute("googleChart", googleChart);
		
		return "admin/adminMain";
	}
	
	@ResponseBody
	@GetMapping("/countChartData")
	public List<GoogleChartVO> countChartData(){
		return dashBoardService.findUserCount();
	}
	
}
