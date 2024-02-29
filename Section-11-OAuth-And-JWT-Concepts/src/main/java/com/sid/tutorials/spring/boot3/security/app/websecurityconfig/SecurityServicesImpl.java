/**
 *
 */
package com.sid.tutorials.spring.boot3.security.app.websecurityconfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

/**
 * @author kunmu
 */
@Service
public class SecurityServicesImpl implements ISecurityServices {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Override
    public boolean loginServices(String username, String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("Username : " + username + " password : " + password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
                userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        boolean authenticated = token.isAuthenticated();
        if (authenticated) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(token);
            securityContextRepository.saveContext(context,httpServletRequest,httpServletResponse);
        }
        return authenticated;
    }

}
