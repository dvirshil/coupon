package com.project.Tests;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.project.Beans.Coupon;
import com.project.Beans.CouponType;
import com.project.Dao.Impl.CouponDBDAO;
import com.project.Dao.Impl.CustomerDBDAO;
import com.project.Facade.CustomerFacade;

public class CoponDBDAOTest {

	
	public static void main(String[] args) throws Throwable {

//	createCoupon();
	//getCoupon();
	
	//getAllCoupons();
		//getCouponByType();
		//getAllCompanyCoupons();
		//isTitleExsist();
		purchesecoupon();
	}

	private static void purchesecoupon() throws Throwable {
		Coupon coupon=new Coupon();
		coupon.setId((long) 6);
		CustomerFacade cf =  new CustomerFacade();
		cf.purchasceCoupon(coupon);
		
	}

	private static void isTitleExsist() throws Exception {
		CouponDBDAO coupon=new CouponDBDAO();		
	coupon.isTitleExsist("zivziv");
	}

	private static void getAllCompanyCoupons() throws Exception {
		CouponDBDAO coupDao = new CouponDBDAO();
		coupDao.getAllCompanyCoupons((long) 1);
		
	}

	private static void getCouponByType() throws Exception {
		CouponDBDAO coupDao = new CouponDBDAO();
		
		coupDao.getCouponsByType(CouponType.FOOD);
		
	}

	private static void getAllCoupons() throws Exception {
		
		CouponDBDAO coupDao = new CouponDBDAO();
		coupDao.getAllCoupons();
		
		
	}

	private static void getCoupon() throws Exception {

		CouponDBDAO coupDao = new CouponDBDAO();
		
		System.out.println(coupDao.getCoupon(1).toString());
		
	}

	private static void createCoupon() throws SQLException, Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Coupon coup=new Coupon("aasssaa", simpleDateFormat.parse("13/05/2017"), simpleDateFormat.parse("18/05/2017"), 10, CouponType.FOOD ,"sssss", 3.25, "http;//");
		CouponDBDAO coupDao = new CouponDBDAO();
		
		coupDao.createCoupon(coup);
		}

}
