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
import com.project.Dao.Impl.CouponDBDAO;
import com.project.Facade.CustomerFacade;

@Path("/customer")
public class CustomerController {

	Customer customer=new Customer();
	Coupon coupon=new Coupon();
	CustomerFacade customerFacade=new CustomerFacade();
	CouponDBDAO couponDBDAO = new CouponDBDAO();
	Collection<Coupon> coupons = null;
	

	
	

	@POST
	@Path("/purchaseCoupon")
	@Produces(MediaType.TEXT_PLAIN)
	public String purchaseCoupon(@FormParam("couponId") long couponId,
								@FormParam ("purchase") String username) throws Throwable {
		customerFacade.customer.setCust_name(username);
		coupon.setId(couponId);
		customerFacade.purchasceCoupon(coupon);
		return "purchase coupon - customer.html";
	}

	@POST
	@Path("/getAllPurchasedCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCoupons(@FormParam ("allPurchasedCoupons") String username) throws Exception {

		customerFacade.customer.setCust_name(username);
		
		return customerFacade.getAllPurchasedCoupon();

	}

	@POST
	@Path("/getAllPurchasedCouponsByType")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCouponsByType( @FormParam("couponType") String couponType,
															@FormParam ("getCouponsByType") String username) throws Throwable {
		customerFacade.customer.setCust_name(username);
		CouponType type= CouponType.valueOf(couponType.toUpperCase());
		
		return customerFacade.getAllPurchasedCouponbyType(type);

	}

	@POST
	@Path("/getAllPurchasedCouponsByPrice")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllPurchasedCouponsByPrice(@FormParam("price") double price,
															@FormParam ("getCouponsByPrice") String username) throws SQLException, ParseException {

		customerFacade.customer.setCust_name(username);
	return  customerFacade.getAllPurchasedCouponbyPrice(price);
	}

}
