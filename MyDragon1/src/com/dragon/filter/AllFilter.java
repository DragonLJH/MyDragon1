package com.dragon.filter;

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
import javax.servlet.http.HttpSession;

import com.dragon.common.StringConstant;

/**
 * Servlet Filter implementation class AllFilter
 */
@WebFilter(urlPatterns={"/borrowrecord","/management","/discuss"})
public class AllFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AllFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		request.setCharacterEncoding("utf-8"); // 设置请求数据的解码字符集
		if(session.getAttribute(StringConstant.SESSION_STUDENTID) == null) {
			resp.sendRedirect("LoginRegist.html");
			return;
		}
		chain.doFilter(request, response);
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
