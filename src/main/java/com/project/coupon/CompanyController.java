package com.project.coupon;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.websocket.server.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.project.Beans.Coupon;
import com.project.Beans.CouponType;
import com.project.Facade.CompanyFacade;
import com.sun.research.ws.wadl.Request;

@Path("/company")
public class CompanyController {
	

	
	 private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 Coupon coupon=new Coupon();
	 CompanyFacade companyFacade=new CompanyFacade();
	 Collection<Coupon> coupons = null;
	 
	@POST
	@Path("/createCoupon")
	@Produces(MediaType.TEXT_PLAIN)
	public String createCoupon(@FormParam("title") String title,
								@FormParam("startDate") String startDate,
								@FormParam("endDate") String endDate,
								@FormParam("amount") Integer amount,
								@FormParam("type") String type,
								@FormParam("message") String message,
								@FormParam("price") double price,
								@FormParam("image") String image) throws Exception{
		
		 
					//Change from String to Date.				
					Date sDate = sdf.parse(startDate);
					Date eDate=sdf.parse(endDate);
					//Change from String to CuponType.
					CouponType couponType = null;
					for (CouponType ct : CouponType.values()){
						if (ct.name().equals(type)){
							couponType = ct;
						}
					}
					
					coupon.setTitle(title);
					coupon.setStart_date(sDate);
					coupon.setEnd_date(eDate);
					coupon.setAmount(amount);
					coupon.setType(couponType);
					coupon.setMessage(message);
					coupon.setPrice(price);
					coupon.setImage(image);
					
					companyFacade.createCoupon(coupon);
					
					return "create coupon - company.html";
	}	
	
	@POST
	@Path("/removeCoupon")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCoupon(@FormParam("couponId") long couponId) throws SQLException {
		coupon.setId(couponId);
		companyFacade.removeCoupon(coupon);
		
		return "remove coupon - company.html";
		
		}
	
	
	@POST
	@Path("/updateCoupon")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCoupon(@FormParam("couponId") Long couponId,
								@FormParam("title") String title,
								@FormParam("startDate") String startDate,
								@FormParam("endDate") String endDate,
								@FormParam("amount") int amount,
								@FormParam("type") String type,
								@FormParam("message") String message,
								@FormParam("price") Double price,
								@FormParam("image") String image) throws SQLException, ParseException{
		
		Date eDate=sdf.parse(endDate);  // change from String to Date
		Date sDate=sdf.parse(startDate); // change from String to Date
		CouponType ct=CouponType.valueOf(type); //change from String to CouponType
		
		coupon.setId(couponId);
		coupon.setTitle(title);
		coupon.setStart_date(sDate);
		coupon.setEnd_date(eDate);
		coupon.setAmount(amount);
		coupon.setType(ct);
		coupon.setMessage(message);
		coupon.setPrice(price);
		coupon.setImage(image);
		
		companyFacade.updateCoupon(coupon);
		
						
		return "update coupon - company.html";
	}
	
	
	@POST
	@Path("/getCouponById")
	@Produces(MediaType.TEXT_PLAIN)
	public Coupon getCouponById(@FormParam("couponId") long couponId,
								@FormParam ("usernamelog") String username) throws Exception {
	System.out.println("the user name is:" + username);
	
	companyFacade.getCoupon(couponId);
		System.out.println("get coupon by id - company.html");
		
	return coupon;
	}
	
	////////////TODO

	@POST
	@Path("/getCouponsByType")
	@Produces(MediaType.TEXT_PLAIN)
	public Collection<Coupon> getCouponsByType(@FormParam("companyId") Long companyId,
												@FormParam("couponType") String couponType) throws Exception{
		
		CouponType ct=CouponType.valueOf(couponType);
		
		Collection<Coupon> coupon=companyFacade.getAllCouponByType(ct, companyId);

		return coupon;
		
	}
	
	

	
}
