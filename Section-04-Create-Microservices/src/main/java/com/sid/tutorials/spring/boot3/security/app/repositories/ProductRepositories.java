/**
 * 
 */
package com.sid.tutorials.spring.boot3.security.app.repositories;

import com.sid.tutorials.spring.boot3.security.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kunmu
 *
 */
public interface ProductRepositories extends JpaRepository<Product, Long> {

}
