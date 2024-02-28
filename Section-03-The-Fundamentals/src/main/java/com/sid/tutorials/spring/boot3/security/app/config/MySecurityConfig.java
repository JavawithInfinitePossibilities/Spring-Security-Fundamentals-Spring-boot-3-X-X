/**
 *
 */
package com.sid.tutorials.spring.boot3.security.app.config;


import com.sid.tutorials.spring.boot3.security.app.filter.MySecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Lenovo
 */
@Configuration
public class MySecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyAuthenticationProvider authenticationProvider;

    @Autowired
    private MySecurityFilter filter;

    /*@Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager userDetailsInmemory = new InMemoryUserDetailsManager();
        UserDetails userDetails = User.withUsername("sid")
                .password(passwordEncoder.encode("sahu"))
                .authorities("read")
                .build();
        userDetailsInmemory.createUser(userDetails);
        return userDetailsInmemory;
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        /*http.formLogin();*/
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated());
        http.addFilterBefore(filter, BasicAuthenticationFilter.class);
        return http.build();
    }
}
