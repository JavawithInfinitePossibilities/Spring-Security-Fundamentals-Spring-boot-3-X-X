/**
 *
 */
package com.sid.tutorials.spring.boot3.security.app.controller;

import com.sid.tutorials.spring.boot3.security.app.model.Coupon;
import com.sid.tutorials.spring.boot3.security.app.repositories.CouponRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kunmu
 * URL:
 *          http://localhost:8090/Section-06-Secure-Webapp-Couponservices/
 *
 */
@Controller
public class CouponController {

    @Autowired
    private CouponRepositories couponRepositories;

    @GetMapping("/showCreateCoupon")
    public String showCreateCoupon() {
        return "createCoupon";
    }

    @PostMapping("/saveCoupon")
    public String saveCoupon(Coupon coupon) {
        Coupon couponSave = couponRepositories.save(coupon);
        return "createResponse";
    }

    @GetMapping("/showGetCoupon")
    public String showGetCoupon() {
        return "getCoupon";
    }

    @GetMapping("/getCoupon")
    public ModelAndView getCoupon(String code) {
        ModelAndView modelAndView = new ModelAndView("couponDetails");
        Coupon couponSave = couponRepositories.findByCode(code);
        modelAndView.addObject(couponSave);
        return modelAndView;
    }
}
