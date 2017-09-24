package com.project.Facade;

import java.util.Collection;

import com.project.Beans.Coupon;
import com.project.Dao.CompanyDAO;
import com.project.Dao.CouponDAO;
import com.project.Dao.CustomerDAO;
import com.project.Dao.Impl.CompanyDBDAO;
import com.project.Dao.Impl.CouponDBDAO;
import com.project.Dao.Impl.CustomerDBDAO;
import com.project.Dao.Impl.DataVallidation;

public class DailyCouponExpirationTask implements Runnable {

	private CouponDAO couponDAO;
	private CustomerDAO customerDAO;
	private CompanyDAO companyDAO;
	private DataVallidation dataVall;
	 static boolean quit;

	public DailyCouponExpirationTask() {
		couponDAO= CouponDBDAO.getInstance();
		customerDAO=CustomerDBDAO.getInstance();
		companyDAO= CompanyDBDAO.getInstance();
	}

	@Override
	public void run() {
		try {
			Collection<Coupon> Coupons = couponDAO.getAllCoupons();
			while (Coupons.iterator().hasNext()) {
				if (dataVall.couponDateIsVallid(Coupons.iterator().next()) == false) {
					couponDAO.removeCoupon(Coupons.iterator().next());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void stopTask() {
		quit = true;
		System.out.println("STOPING THE DAILY TASK");

	}

}
