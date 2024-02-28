/**
 * 
 */
package com.sid.tutorials.spring.boot3.security.app.websecurityconfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author kunmu
 *
 */
public interface ISecurityServices {

	boolean loginServices(String username, String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
