package com.sid.tutorials.spring.boot3.security.app;

import com.sid.tutorials.spring.boot3.security.app.bean.Car;
import com.sid.tutorials.spring.boot3.security.app.bean.Person;
import com.sid.tutorials.spring.boot3.security.app.mockdata.MockDataPrep;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kunmu On 28-02-2024
 */
@SpringBootTest(classes = Section09SecurityTesting.class)
@AutoConfigureMockMvc
class Section09SecurityTestingTest {
    @Autowired
    private MockDataPrep mockDataPrep;
    @Autowired
    private MockMvc mockMvc;

    @Disabled
    @Test
    void getPeople() {
        try {
            List<Person> people = mockDataPrep.getPeople();
            people.stream().forEach(p -> System.out.println(p.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Disabled
    @Test
    void getCars() {
        try {
            List<Car> cars = mockDataPrep.getCars();
            cars.stream().forEach(car -> System.out.println(car.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetCouponWithoutAuth() throws Exception {
        mockMvc.perform(get("/couponapi/getcoupon/NEWYEAR")).andExpect(status().isOk());
    }

    @Test
    public void testGetCouponWithoutAuthForbidden() throws Exception {
        mockMvc.perform(get("/couponapi/getcoupon/NEWYEAR")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    public void testGetCouponWithAuth() throws Exception {
        mockMvc.perform(get("/couponapi/getcoupon/NEWYEAR")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void testGetCouponWithAuthRole() throws Exception {
        mockMvc.perform(get("/couponapi/getcoupon/NEWYEAR"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"code\":\"NEWYEAR\",\"discount\":20.00,\"expDate\":\"22062024\"}"));
    }

    @Test
    public void testGetCouponWithoutAuthCSRFForbidden() throws Exception {
        mockMvc.perform(get("/couponapi/getcoupon/NEWYEAR")).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = {"USER"})
    public void testGetCouponWithoutAuthCSRF() throws Exception {
        mockMvc.perform(get("/couponapi/getcoupon/NEWYEAR")
                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"code\":\"NEWYEAR\",\"discount\":20.00,\"expDate\":\"22062024\"}"));
    }
}