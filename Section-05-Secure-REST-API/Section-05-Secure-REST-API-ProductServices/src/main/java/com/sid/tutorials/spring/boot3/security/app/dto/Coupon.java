package com.sid.tutorials.spring.boot3.security.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Coupon {

	private Long id;
	private String code;
	private BigDecimal discount;
	private String expDate;
}
