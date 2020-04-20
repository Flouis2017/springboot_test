package com.flouis.filter.demo.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "myFilter", urlPatterns = "/*")
@Order(1)
public class MyFilter implements Filter {

	@Value("${open.url}")
	private String openUrl;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter beginning");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		/*HttpServletRequest request = (HttpServletRequest) servletRequest;
		String uri = request.getRequestURI();
		String method = request.getMethod();
		System.out.println(uri + "==> " + method);
		filterChain.doFilter(servletRequest, servletResponse);*/

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String requestUrl = request.getRequestURI();
		System.out.println("Filter MyFilter filtered request: " + requestUrl);

		// 校验是否是开放的api，是则放行，不是再校验token
		PathMatcher matcher = new AntPathMatcher();
		if (matcher.match(openUrl, requestUrl)){
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			// 这里是将token藏于请求头中，暂时不对token进行任何处理，只要有token就放行，否则将请求转发至/api/open/unLogin
			String token = request.getHeader("token");
			if (StringUtils.isEmpty(token)){
				servletRequest.getRequestDispatcher("/api/open/unLogin").forward(servletRequest, servletResponse);
			} else {
				filterChain.doFilter(servletRequest, servletResponse);
			}
		}

	}

	@Override
	public void destroy() {
		System.out.println("MyFilter ending");
	}

}
