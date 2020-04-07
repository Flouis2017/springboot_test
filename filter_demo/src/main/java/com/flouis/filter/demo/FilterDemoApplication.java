package com.flouis.filter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FilterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterDemoApplication.class);
	}

}
