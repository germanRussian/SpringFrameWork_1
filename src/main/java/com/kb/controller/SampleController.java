package com.kb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kb.domain.SampleDTO;
import com.kb.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample/*") // 적용 예시 : [/WEB-INF/jsp/sample/aaa.jsp] jsp폴더 안에 jsp 파일 경로.
@Controller
public class SampleController {

	//void, string, @ResponseBody, ResponseEntity<String>에 대한 학습.
	
	
	@RequestMapping("/")
	public void basic() {
		log.info("----------------");
	}

	@GetMapping("/basicOnlyGet") // get방식으로 매핑
	public void basicGet() {
		log.info("----------------GET");
	}

	@PostMapping("/basicOnlyPost") // Post방식으로 매핑
	public void basicGet1() {
		log.info("----------------post");
	}

	
	
	
	// http://localhost/sample/ex01?name=이종혁&age=10; 주소창에 이렇게 주면 SampleDTO에서 정보를 받음.
	@GetMapping("/ex01") 
	public String ex01(SampleDTO dto) {
		log.info("bb");
		log.info(dto);
		return "ex01";

	}

	 // http://localhost/sample/ex02?name=이종혁; 파라메타를 가져와서 스트링에 넣겠다.
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		return "ex02";

	}

	
	// http://localhost/sample/ex03?name=이종혁&name=이순신&name=장군 ; 배열로 넘어온다.
	@GetMapping("/ex03") 
	public String ex03(@RequestParam("name") ArrayList<String> names) {
		log.info(names);
		return "ex03";
	}

	
	
	//ex04에 대한 것
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, 
					new CustomDateEditor(dateFormat, true));
	}
	
	
	//http://localhost/sample/ex04?title=제목&dueDate=2020-05-22
	@GetMapping("/ex04")
	public String ex04(TodoDTO todo) {
		log.info(todo);
		return "ex04";
	}
	
	
	
	// 2.view페이지로 값을 전달하는 방식. / model 이용
	//request.dispatcher //값을 보여줘라
	//http://localhost/sample/ex05?name=제목&page=1
	@GetMapping("/ex05")
	public String ex05(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info(dto);
		log.info(page);
		return "sample/ex05";
	}
	
	// 3.페이지로 이동해라
	//response.RedirectAttributes("aa", aa);
	//http://localhost/sample/ex06?name=제목&age=100
	@GetMapping("/ex06")
	public String ex06(String name, int age, RedirectAttributes rttr) {
		rttr.addFlashAttribute("name", name);
		rttr.addFlashAttribute("age", age);
		return "redirect:/";
		
	}
	
	
	//http://localhost/sample/ex07
	@GetMapping("/ex07")
	public String ex07( RedirectAttributes rttr) {
		rttr.addFlashAttribute("name", "이종혁");
		rttr.addFlashAttribute("age", 300);
		//return "redirect:/";
		return "redirect:/sample/ex00";
		
	}

	@GetMapping("/ex00")
	public String ex00() {
		return "sample/ex05";
	}
	
	// 4.리턴 : 객체 타입
	//이것은 json 데이터이다.
	@GetMapping("/ex08")
	public @ResponseBody SampleDTO ex08() {
		log.info("/ex08----------");
		SampleDTO dto = new SampleDTO();
		dto.setAge(200);
		dto.setName("이종혁");
		return dto;
		
	}
	
	
	@GetMapping("/ex09")
	public ResponseEntity<String> ex09() {
		log.info("/ex09----------");
		String msg = "{\"name\":\"이종혁\",\"age\":222222}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", "application/json;charset=utf-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
		
	}
	
	
}
