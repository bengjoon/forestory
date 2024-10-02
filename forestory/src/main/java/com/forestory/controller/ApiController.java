package com.forestory.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.forestory.domain.PlntIlstrSearch;
import com.forestory.service.ApiService;

@Controller
public class ApiController {
	
	@Autowired
	ApiService apiService;
	
	@GetMapping("/apitest")
	public String apiUpdate() throws Exception {
		
		System.out.println(apiService.plntIlstrSearchUpdate());
		return "성공";
	}
	
	@GetMapping("/plantIlstr")
	public String plnatIlstr(String keyword, Model model, @PageableDefault(page = 0, size = 20, sort = "plantPilbkNo", direction = Sort.Direction.ASC) Pageable pageable) {
		
		Page<PlntIlstrSearch> plntIlstrList  = apiService.plntList(keyword, pageable);
		
		int startPage = Math.max(1, plntIlstrList.getPageable().getPageNumber() - 10);
		int endPage = Math.min(plntIlstrList.getTotalPages(), plntIlstrList.getPageable().getPageNumber() + 10);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("plntIlstrList",plntIlstrList);
		model.addAttribute("keyword",keyword);
		
		return "client/plantIlstr/plantIlstr";
	}
	
	@GetMapping("/plantIlstr/{plantPilbkNo}")
	public String plnatIlstrDetail(@PathVariable long plantPilbkNo ,Model model) {
		PlntIlstrSearch pln = apiService.plntDetail(plantPilbkNo);
		model.addAttribute("pln", pln);
		return "client/plantIlstr/plantIlstrDetail";
	}
	
	
}
