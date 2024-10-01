package com.forestory.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forestory.domain.PlntIlstrSearch;
import com.forestory.dto.OpenApiDTO;
import com.forestory.service.ApiService;

@Controller
public class ApiController {
	
	@Autowired
	ApiService apiService;
	
	@GetMapping("/apitest")
	void apiUpdate() throws Exception {
//		OpenApiDTO openApi = OpenApiDTO.builder()
//									.siteName("http://openapi.nature.go.kr/openapi/service/rest/PlantService/plntIlstrSearch")
//									.surviceKey("4TNaRbWvrkhc0m1QoK9DXROdpO8GcmzIrTE4xnpXo8lJ0pie8T0eH7kcPEEKPz4cOOkGUs7pIE%2B35B82fW3m9g%3D%3D")
//									.contentType("application/xml")
//									.method("GET")
//									.build();
		
		apiService.plntIlstrSearchUpdate();
		
	}
	
	@GetMapping("/plantIlstr")
	public String plnatIlstr(Model model) {
		List<PlntIlstrSearch> plntIlstrList  = apiService.plntList();
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
