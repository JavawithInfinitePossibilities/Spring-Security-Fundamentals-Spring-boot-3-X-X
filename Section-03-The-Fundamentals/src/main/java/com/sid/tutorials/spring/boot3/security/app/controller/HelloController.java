/**
 * 
 */
package com.sid.tutorials.spring.boot3.security.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kunmu
 * URL:http://localhost:8080/Section-03-The-Fundamentals/hello
 */
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Welcome to Spring security!!!";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Spring security with spring boot!!!";
	}
}
