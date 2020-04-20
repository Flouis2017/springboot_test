package com.flouis.interceptor.demo;

import org.junit.Test;
import org.springframework.util.StringUtils;

public class MainTest {

	@Test
	public void test(){
		System.out.println(Thread.currentThread().getName());
		String str = "   ";
		System.out.println(StringUtils.isEmpty(str.trim()));
	}

}
