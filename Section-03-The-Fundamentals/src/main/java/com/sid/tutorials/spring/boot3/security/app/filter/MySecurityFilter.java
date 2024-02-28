/**
 * 
 */
package com.sid.tutorials.spring.boot3.security.app.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Lenovo
 *
 */
@Component
public class MySecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Before request!!!");
		chain.doFilter(request, response);
		System.out.println("After Request!!!");
	}

}
