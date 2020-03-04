package com.mashibin.spring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


/**
 * 用户权限处理
 * @author hukongwen
 *
 */
@Component
@WebFilter(urlPatterns="/*")
public class AccountFilter implements Filter{

	//不需要登陆过滤的URI
	private static final String[] IGNORE_URI= {"/index","/css/","/js/","/images/","/account/validataAccount","/account/login","/account/logOut"};
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Object account = req.getSession().getAttribute("account");
		
		
		String URI = req.getRequestURI();
		//System.out.println("++++++Filter URI++++++"+URI);
		
		boolean pass = canPassIgnore(URI);
		if(pass)
		{
			chain.doFilter(req, resp);
			return;
		}
		
		if(null == account)
		{
			resp.sendRedirect("/account/login");
		}else
		{
			chain.doFilter(req, resp);
		}
		
	}

	private boolean canPassIgnore(String URI) {
		// TODO Auto-generated method stub
		for(String uri : IGNORE_URI)
		{
			if(URI.startsWith(uri))
				return true;
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("++++++++++filter init++++++++++");
		
		Filter.super.init(filterConfig);
	}

}
