package com.flouis.interceptor.demo.controller;

import com.flouis.interceptor.demo.base.JsonResult;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

	@GetMapping("/open/home")
	public JsonResult home(){
		Map<String, String> res = Maps.newHashMap();
		res.put("游客", "欢迎来到首页！");
		return JsonResult.success(res);
	}

	@GetMapping("/open/unLogin")
	public JsonResult unLogin(){
		return JsonResult.fail("尚未登录！");
	}

}
