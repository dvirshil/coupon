package com.project.Dao.Impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.project.Beans.Company;
import com.project.Beans.Coupon;
import com.project.Beans.Customer;
import com.project.Dao.CouponDAO;
import com.project.Dao.CustomerDAO;

public class DataVallidation {
	CustomerDAO customerDao;
	CouponDAO couponDao;
 public	Customer customer=new Customer();
	CompanyDBDAO companyDBDao=new CompanyDBDAO();

	
	public DataVallidation() throws Exception, Throwable{
		couponDao= CouponDBDAO.getInstance();
		customerDao=CustomerDBDAO.getInstance();
		customer=  new Customer();
			
	}
	
	public Boolean customerHasOne(Coupon coupon) throws Exception, Throwable {
		Collection<Coupon> customersCoupons= new ArrayList<Coupon>();
		customersCoupons=customerDao.getCouponsByCustomer(customer);
		Long id = coupon.getId();
		while (customersCoupons.iterator().hasNext()){
			if( customersCoupons.iterator().next().getId() == id) {
				System.out.println("You already purchased this coupon");
				return true;
			}	
		}
		return false;

	}
	
	public Boolean couponDateIsVallid(Coupon coupon) throws Exception, Exception {
		
		Coupon chechkCoup=couponDao.getCoupon(coupon.getId());
		Date date = new Date();
	
		if(chechkCoup.getEnd_date().after(date)){
			return true;
		}
		else{
		System.out.println("Coupon has expired ");
			return false;
		}
	}
		
	public Boolean couponInStock(Coupon coupon) throws Exception, Exception {
			Coupon chechkCoupStock=couponDao.getCoupon(coupon.getId());
			if(chechkCoupStock.getId()!=null && chechkCoupStock.getAmount()>=1){
				return true ;
			}
			else{
				System.out.println("Coupon out of stock");
				return false;
			}
		}
	
	public Boolean cheackCompany(String companyName, String companyPassword) throws SQLException {
		Collection<Company> company = companyDBDao.getAllCompanies();
		
		for(Company companys:company) {
			if(companys.getComp_name()==companyName || companys.getPassword()==companyPassword) {
				return false;
			}
					
	}
		return true;
			
	}
}

