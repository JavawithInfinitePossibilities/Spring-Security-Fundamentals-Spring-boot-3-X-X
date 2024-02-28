/**
 * 
 */
package com.sid.tutorials.spring.boot3.security.app.repositories;

import com.sid.tutorials.spring.boot3.security.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kunmu
 *
 */
public interface RoleRepositories extends JpaRepository<Role, Long> {

}
