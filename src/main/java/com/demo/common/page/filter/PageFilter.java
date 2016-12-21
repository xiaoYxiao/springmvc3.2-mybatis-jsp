package com.demo.common.page.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.demo.common.page.context.ExtremeContext;
import com.demo.common.page.context.PageContext;

public class PageFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		//HttpServletResponse resp = (HttpServletResponse)response;
		PageContext.setPageParameters(getCurrentPage(req), getPageSize(req), getPropertyAndSort(req));
		
		try {
			chain.doFilter(req, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			PageContext.remove();
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}
	
	private Integer getCurrentPage(HttpServletRequest request) {
		return ExtremeContext.getCurrentPage(request);
	}
	
	private Integer getPageSize(HttpServletRequest request) {
		return ExtremeContext.getPageSize(request);
	}
	
	private String[] getPropertyAndSort(HttpServletRequest request) {
		return ExtremeContext.getPropertyAndSort(request);
	}
	
}
