package com.project.coupon;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.Beans.Coupon;
import com.project.Beans.CouponType;
import com.project.Beans.Customer;
import com.project.Facade.CustomerFacade;

@Path("/customer")
public class CustomerController {

	Customer customer=new Customer();
	Coupon coupon=new Coupon();
	CustomerFacade customerFacade=new CustomerFacade();
	Collection<Coupon> coupons = null;
	
	
	@POST
	@Path("/purchaseCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	public String purchaseCoupon(@FormParam("couponId") long couponId) throws Throwable {
		
		coupon.setId(couponId);
		customerFacade.purchasceCoupon(coupon);
		
		return "purchase coupon - customer.html";
	}

	@GET
	@Path("/getAllPurchasedCoupons")
	public Collection<Coupon> getAllPurchasedCoupons() throws Exception {
		Collection<Coupon> coupon=new ArrayList();
		customerFacade.getAllPurchasedCoupon();
		return coupon;

	}

	@POST
	@Path("/getAllPurchasedCouponsByType")
	@Produces(MediaType.TEXT_PLAIN)
	public Collection<Coupon> getAllPurchasedCouponsByType(@FormParam("couponType") String couponType) throws Throwable {
		
		String ct=couponType;
		CouponType type= CouponType.valueOf(ct.toUpperCase());
		
		System.out.println("get all purchased coupons by type - customer.html");
		return customerFacade.getAllPurchasedCouponbyType(type);

	}

	@POST
	@Path("/getAllPurchasedCouponsByPrice")
	@Produces(MediaType.TEXT_PLAIN)
	public Collection<Coupon> getAllPurchasedCouponsByPrice(@FormParam("price") double price) throws SQLException, ParseException {
		
		return customerFacade.getAllPurchasedCouponbyPrice(price);
	}


}
