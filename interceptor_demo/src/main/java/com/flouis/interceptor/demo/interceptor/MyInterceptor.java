package com.flouis.interceptor.demo.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

	@Value("${open.url}")
	private String openUrl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("MyInterceptor... Before Controller");
		String requestUrl = request.getRequestURI();
		System.out.println("MyInterceptor intercept url: " + requestUrl);

		// token校验——因为是演示，所以只要token不为空就放行，否则请求转发至/api/open/unLogin
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)){
			request.getRequestDispatcher("/api/open/unLogin").forward(request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("MyInterceptor... After Controller");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("MyInterceptor... After the whole request so as DispatcherServlet return view");
	}


}
