/**
 * 
 */
package com.sid.tutorials.spring.boot3.security.app.repositories;

import com.sid.tutorials.spring.boot3.security.app.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kunmu
 *
 */
public interface CouponRepositories extends JpaRepository<Coupon, Long> {

	Coupon getByCode(String couponCode);

}
