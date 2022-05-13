package com.kb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;


@Log4j
@RequestMapping("/sample/*") // 적용 예시 : [/WEB-INF/jsp/sample/aaa.jsp] jsp폴더 안에 jsp 파일 경로.
@Controller
public class SampleController {
	
	
	@RequestMapping("")
	public void basic(){
		log.info("----------------");
	}

}
