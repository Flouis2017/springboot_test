package com.flouis.filter.demo.controller;

import com.flouis.filter.demo.base.JsonResult;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

	@GetMapping("/open/unLogin")
	public JsonResult unLogin(){
		return JsonResult.fail("尚未登录！");
	}

	@GetMapping("/open/home")
	public JsonResult home(){
		Map<String, String> resMap = Maps.newHashMap();
		resMap.put("游客", "欢迎访问首页！");
		return JsonResult.success(resMap);
	}

}
