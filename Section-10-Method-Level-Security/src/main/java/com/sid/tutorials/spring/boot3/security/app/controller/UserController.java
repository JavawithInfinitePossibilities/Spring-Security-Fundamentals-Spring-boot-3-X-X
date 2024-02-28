/**
 *
 */
package com.sid.tutorials.spring.boot3.security.app.controller;

import com.sid.tutorials.spring.boot3.security.app.model.User;
import com.sid.tutorials.spring.boot3.security.app.repositories.UserRepositories;
import com.sid.tutorials.spring.boot3.security.app.websecurityconfig.ISecurityServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author kunmu
 */
@Controller
@EnableMethodSecurity(prePostEnabled = true)
public class UserController {

    @Autowired
    private ISecurityServices securityServices;

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public String login(String email, String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        boolean loginResponse = securityServices.loginServices(email, password, httpServletRequest, httpServletResponse);
        if (loginResponse) {
            System.out.println("Login successfull!!!!");
            return "index";
        }
        System.out.println("Login Unsuccessfull!!!!");
        return "login";
    }

    @GetMapping("/showReg")
    @PreAuthorize("permitAll()")
    public String showReg() {
        return "registerUser";
    }

    @PostMapping("/registerUser")
    @PreAuthorize("permitAll()")
    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepositories.save(user);
        return "login";
    }
}
