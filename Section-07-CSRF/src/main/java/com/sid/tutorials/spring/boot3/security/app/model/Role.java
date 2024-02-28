package com.sid.tutorials.spring.boot3.security.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	/**
	 * Bi-Directional relationship will create a cyclic dependency which create a
	 * infinite call to both the class. Due to which the application will fall in to
	 * a stackoverflow error.
	 */
	/*@ManyToMany(mappedBy = "roles")
	private Set<User> users;*/

	@Override
	public String getAuthority() {
		return name;
	}
}
