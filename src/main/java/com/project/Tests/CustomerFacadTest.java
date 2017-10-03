package com.project.Tests;

import java.text.SimpleDateFormat;

import com.project.Beans.Coupon;
import com.project.Beans.CouponType;
import com.project.Facade.CustomerFacade;


public class CustomerFacadTest {


	public static void main(String[] args) throws Throwable {
		purchasCoup();
	}
		
	public static void purchasCoup() throws Throwable {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");

		CustomerFacade cf = new CustomerFacade();
		Coupon coupon=new Coupon("aasssaa", simpleDateFormat.parse("13/05/2017"), simpleDateFormat.parse("18/05/2017"), 10, CouponType.FOOD ,"sssss", 3.25, "http;//");
		coupon.setId((long) 6);
		cf.purchasceCoupon(coupon);
	}

}
