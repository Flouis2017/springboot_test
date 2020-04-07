package com.flouis.filter.demo.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "myFilter", urlPatterns = "/*")
@Order(1)
public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter beginning");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String uri = request.getRequestURI();
		String method = request.getMethod();
		System.out.println(uri + "==> " + method);
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		System.out.println("MyFilter ending");
	}
}
