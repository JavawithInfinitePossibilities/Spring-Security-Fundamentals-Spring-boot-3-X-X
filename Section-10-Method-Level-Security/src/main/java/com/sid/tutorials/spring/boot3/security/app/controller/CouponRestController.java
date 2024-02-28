/**
 *
 */
package com.sid.tutorials.spring.boot3.security.app.controller;

import com.sid.tutorials.spring.boot3.security.app.model.Coupon;
import com.sid.tutorials.spring.boot3.security.app.repositories.CouponRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

/**
 * @author kunmu <br/>
 * URL:
 * http://localhost:8090/Section-09-Security-Testing/couponapi/getcoupon/NEWYEAR
 * http://localhost:8090/Section-09-Security-Testing/couponapi/couponsave
 * {
 * "id": 1,
 * "code": "NEWYEAR",
 * "discount": 20.00,
 * "expDate": "22062024"
 * }
 */
@RestController
@RequestMapping("/couponapi")
@EnableMethodSecurity(prePostEnabled = true)
public class CouponRestController {

    @Autowired
    private CouponRepositories couponRepositories;

    @RequestMapping(value = "/couponsave", method = RequestMethod.POST, consumes = "application/json")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponRepositories.save(coupon);
    }

    @RequestMapping(value = "/getcoupon/{couponCode}", method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    public Coupon getCoupon(@PathVariable("couponCode") String couponCode) {
        return couponRepositories.getByCode(couponCode);
    }
}
