package com.project.Tests;

import com.project.Beans.Coupon;
import com.project.Facade.CustomerFacade;


public class CustomerFacadTest {


	public static void main(String[] args) throws Throwable {
		purchasCoup();
	}
		
	public static void purchasCoup() throws Throwable {
		CustomerFacade cf = new CustomerFacade();
		Coupon coupon=new Coupon();
		coupon.setId((long) 1);
		cf.purchasceCoupon(coupon);
	}

}
