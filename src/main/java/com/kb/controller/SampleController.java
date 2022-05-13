package com.kb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;


@Log4j
@RequestMapping("/sample/")//도메인.sample/jsp파일
@Controller
public class SampleController {
	
	
	@RequestMapping("")
	public void basic(){
		log.info("----------------");
	}

}
