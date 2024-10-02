package com.forestory.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forestory.domain.PlntIlstrSearch;
import com.forestory.service.ApiService;

@Controller
public class ApiController {
	
	@Autowired
	ApiService apiService;
	
	@GetMapping("/apitest")
	@ResponseBody
	public String apiUpdate() throws Exception {
		
		System.out.println(apiService.plntIlstrSearchUpdate());
		return "식물도감 업데이트";
	}
	
	@GetMapping("/plantIlstr")
	public String plnatIlstr(String keyword, Model model, @RequestParam(defaultValue = "1") int page) {
		int size = 20; //한 페이지에 보여줄 정보 수
		if(page<1) {page=1;}
		Pageable pageable = PageRequest.of(page-1, size);
		Page<PlntIlstrSearch> plntIlstrList  = apiService.plntList(keyword, pageable);
		
		
		int startPage = (plntIlstrList.getPageable().getPageNumber()/10)*10+1;
		int endPage = Math.min(plntIlstrList.getTotalPages(), startPage+9);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("plntIlstrList",plntIlstrList);
		
		return "client/plantIlstr/plantIlstr";
	}
	
	@GetMapping("/plantIlstr/{plantPilbkNo}")
	public String plnatIlstrDetail(@PathVariable long plantPilbkNo ,Model model) {
		PlntIlstrSearch pln = apiService.plntDetail(plantPilbkNo);
		model.addAttribute("pln", pln);
		return "client/plantIlstr/plantIlstrDetail";
	}
	
	
}
