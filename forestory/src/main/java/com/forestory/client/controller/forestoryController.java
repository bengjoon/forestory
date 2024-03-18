package com.forestory.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/forestory/*")
public class forestoryController {
	
	@GetMapping("/forestoryInsertForm")
	public String forestoryInsert() {
		
		log.info("forestoryInsertForm 메소드 호출성공");
		return "client/forestory/forestoryInsertForm";
	}
	
	@GetMapping("/forestoryList")
	public String forestoryList() {
		
		
		log.info("forestoryList 메소드 호출성공");
		return "client/forestory/forestoryList";
	}
}
