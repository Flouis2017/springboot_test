package com.flouis.jsp.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

	@GetMapping("/index")
	public String index(){
		System.out.println("xxxxx");
		return "index";
	}

}
