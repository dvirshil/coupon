package com.project.Tests;

import com.project.Beans.Customer;
import com.project.Facade.ClientType;
import com.project.Facade.CouponSystemSingelton;
import com.project.Facade.CustomerFacade;

public class CouponSystemTest {

	private static CustomerFacade cf = new CustomerFacade();
	
	public static void main(String[] args) throws Exception {
		CouponSystemSingelton css= new CouponSystemSingelton();
		Customer dvir= new Customer();
		css.login("Dvir", "QAZWSX12", ClientType.CUSTOMER);
		cf.getAllPurchasedCoupon();
		
		// TODO Auto-generated method stub

	}

}
