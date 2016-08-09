package com.bitwise.onlineShoppingCart;

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

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Servlet Filter implementation class filterCartLogin
 */
@WebFilter("/filterCartLogin")
public class filterCartLogin implements Filter {



	/**
	 * Default constructor. 
	 */
	public filterCartLogin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession ssn = req.getSession();
		String uname=  (String) ssn.getAttribute("uname");
		System.out.println("ssn:"+ssn);
		
		if(uname==null){
			System.out.println("in1");
			//request.getRequestDispatcher("/login").forward(request, response);
			res.sendRedirect("/SpringOnlineShoppingCart/login");
		}
		else {
			System.out.println("in2");
			chain.doFilter(request, response);
		}	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
