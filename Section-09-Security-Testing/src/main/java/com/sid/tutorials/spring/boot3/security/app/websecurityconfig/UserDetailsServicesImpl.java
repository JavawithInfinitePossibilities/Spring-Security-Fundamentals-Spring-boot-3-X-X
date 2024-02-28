/**
 * 
 */
package com.sid.tutorials.spring.boot3.security.app.websecurityconfig;

import com.sid.tutorials.spring.boot3.security.app.model.User;
import com.sid.tutorials.spring.boot3.security.app.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author kunmu
 *
 */
@Service
public class UserDetailsServicesImpl implements UserDetailsService {

	@Autowired
	private UserRepositories userRepositories;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepositories.findByEmail(username);
		System.out.println("User details : "+user);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("The user is not exist in the system : User name :" + username);
		}
		return new org.springframework.security.core.userdetails
				.User(user.getEmail(), user.getPassword(),user.getRoles());
	}

}
