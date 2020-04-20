package com.flouis.interceptor.demo.controller;

import com.flouis.interceptor.demo.base.JsonResult;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping("/{name}")
	public JsonResult hello(@PathVariable String name){
		Map<String, String> resMap = Maps.newHashMap();
		resMap.put("info", "hello " + name);
		return JsonResult.success(resMap);
	}

}
