/**
 *
 */
package com.sid.tutorials.spring.boot3.security.app.controller;

import com.sid.tutorials.spring.boot3.security.app.model.Coupon;
import com.sid.tutorials.spring.boot3.security.app.repositories.CouponRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kunmu <br/>
 *         URL:
 *         http://localhost:9090/Section-04-Create-Microservices-couponservices/couponapi/getcoupon/NEWYEAR
 *         http://localhost:9090/Section-04-Create-Microservices-couponservices/couponapi/couponsave
 *         {
 *     			"id": 1,
 *     			"code": "NEWYEAR",
 *     			"discount": 20.00,
 *     			"expDate": "22062024"
 *            }
 *
 */
@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    @Autowired
    private CouponRepositories couponRepositories;

    @RequestMapping(value = "/couponsave", method = RequestMethod.POST, consumes = "application/json")
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponRepositories.save(coupon);
    }

    @RequestMapping(value = "/getcoupon/{couponCode}", method = RequestMethod.GET)
    public Coupon getCoupon(@PathVariable("couponCode") String couponCode) {
        return couponRepositories.getByCode(couponCode);
    }
}
