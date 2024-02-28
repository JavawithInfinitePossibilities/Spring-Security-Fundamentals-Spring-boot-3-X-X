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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author kunmu
 */
@Controller
public class UserController {

    @Autowired
    private ISecurityServices securityServices;

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
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
    public String showReg() {
        return "registerUser";
    }

    @PostMapping("/registerUser")
    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveUser = userRepositories.save(user);
        return "login";
    }
}
