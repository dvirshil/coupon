package com.project.coupon;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.Beans.Company;
import com.project.Beans.Coupon;
import com.project.Beans.CouponType;
import com.project.Dao.Impl.CouponDBDAO;
import com.project.Facade.CompanyFacade;

@Path("/company")
public class CompanyController {
	
	private HttpServletRequest request;
	
	 private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	 Coupon coupon=new Coupon();
	 Company company=new Company();
	 CompanyFacade companyFacade=new CompanyFacade();
	 CouponDBDAO couponDBDAO=new CouponDBDAO();
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
								@FormParam("image") String image,
								@FormParam("create") String username) throws Exception{
		
					//Change from String to Date.				
					Date sDate = sdf.parse(startDate);
					Date eDate=sdf.parse(endDate);
					
					CouponType ct=CouponType.valueOf(type); //change from String to CouponType
					
					coupon.setTitle(title);
					coupon.setStart_date(sDate);
					coupon.setEnd_date(eDate);
					coupon.setAmount(amount);
					coupon.setType(ct);
					coupon.setMessage(message);
					coupon.setPrice(price);
					coupon.setImage(image);
					
					companyFacade.company.setComp_name(username);
					companyFacade.createCoupon(coupon);
					
					return "create coupon - company.html";
	}	
	
	@POST
	@Path("/removeCoupon")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCoupon(@FormParam("couponId") long couponId,
								@FormParam ("remove") String username) throws SQLException {
		coupon.setId(couponId);
		companyFacade.company.setComp_name(username);
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
								@FormParam("image") String image,
								@FormParam("update") String username) throws SQLException, ParseException{
		companyFacade.company.setComp_name(username);
		companyFacade.company.setId(couponDBDAO.getCompanyIdByCompanyName(company.getComp_name()));
		
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
		
		System.out.println(company);
		companyFacade.updateCoupon(coupon);
		
						
		return "update coupon - company.html";
	}
	
	
	@POST
	@Path("/getCouponById")
	@Produces(MediaType.TEXT_PLAIN)
	public Coupon getCouponById(@FormParam("couponId") long couponId,
								@FormParam ("getCouponById") String username) throws Exception {
	companyFacade.company.setComp_name(username);
	companyFacade.getCoupon(couponId);
		System.out.println("get coupon by id - company.html");
		
	return coupon;
	}
	

	@POST
	@Path("/getCouponsByType")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByType(	@FormParam("couponType") String couponType,
												@FormParam("getCouponsByType") String username) throws Exception{
		company.setComp_name(username);
		company.setId(couponDBDAO.getCompanyIdByCompanyName(company.getComp_name()));
		
		CouponType ct=CouponType.valueOf(couponType.toUpperCase());
		
		Collection<Coupon> coupon=companyFacade.getAllCouponByType(ct, company.getId());
		return coupon;
		
	}
	
	@POST
	@Path("/getAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllCoupons(@FormParam("getAllCoupons") String username) throws Exception{
		company.setComp_name(username);
		
		coupons=couponDBDAO.getAllCoupons();
		
		return coupons;
		
	}
	
}
