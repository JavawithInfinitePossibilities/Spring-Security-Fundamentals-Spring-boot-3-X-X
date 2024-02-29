/**
 * 
 */
package com.sid.tutorials.spring.boot3.security.app.repositories;

import com.sid.tutorials.spring.boot3.security.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kunmu
 *
 */
public interface UserRepositories extends JpaRepository<User, Long> {
	public User findByEmail(String username);
}
