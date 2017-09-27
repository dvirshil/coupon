package com.project.Facade;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import com.project.Beans.Coupon;
import com.project.Beans.CouponType;
import com.project.Beans.Customer;
import com.project.Dao.CouponDAO;
import com.project.Dao.CustomerDAO;
import com.project.Dao.Impl.CouponDBDAO;
import com.project.Dao.Impl.CustomerDBDAO;
import com.project.Dao.Impl.DataVallidation;


public class CustomerFacade implements CouponClientFacade {
	CustomerDAO customerDao;
	CouponDAO couponDao;
public	Customer customer;

	public CustomerFacade() {
		couponDao = CouponDBDAO.getInstance();
		customerDao = CustomerDBDAO.getInstance();
		customer=new Customer();
	}

	public Customer getCustomerName() {
		return this.customer;
	}

	public void purchasceCoupon(Coupon coupon) throws Throwable {
		coupon=couponDao.getCoupon(coupon.getId());
		DataVallidation vallData = new DataVallidation();
		if (vallData.couponDateIsVallid(coupon) == true && vallData.couponInStock(coupon) == true
				&& vallData.customerHasOne(coupon) == false) {
			
			customerDao.UpdateCustomer_CouponTable(customer, coupon);
			coupon.setAmount(coupon.getAmount() - 1);
			couponDao.updateCoupon(coupon);
			System.out.println("coupon purchased");

		} else {
			throw new SQLException("Purchasing process has failed");
		}

	}

	public void getAllPurchasedCoupon() throws Exception {
			couponDao.getAllCoupons();
	}

	public Collection<Coupon> getAllPurchasedCouponbyType(CouponType type) throws SQLException, Exception {
		return couponDao.getCouponsByType(type);

	}

	public Collection<Coupon> getAllPurchasedCouponbyPrice(double price) throws SQLException, ParseException  {
		
		return	couponDao.getCouponsByPrice(price);

	}

	public Collection<Coupon> getAllCoupons() throws SQLException, Exception {

		return couponDao.getAllCoupons();
	}

	@Override
	public CouponClientFacade login(String custName, String password, ClientType clientType) throws Exception {
		if (customerDao.login(custName, password) == true) {
			System.out.println("successfully logged in");
			customer = customerDao.getCustomerByName(custName);
			return this;
		} else {
			throw new Exception("Failed to login. customer name does not match password");
		}

	}

	public Coupon getCoupon(long id) throws SQLException, Exception {

		return couponDao.getCoupon(id);
	}

}
