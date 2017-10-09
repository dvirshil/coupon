package com.project.Tests;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.Beans.Coupon;
import com.project.Beans.Customer;
import com.project.Dao.Impl.CustomerDBDAO;
import com.project.Dao.Impl.DataVallidation;
import com.project.Facade.CustomerFacade;

public class CustomerDBDAOTest {

	public static void main(String[] args) throws Throwable {
		// create();
		// remove();
		//update();
		//allcust();
		//login();
		//purchasce();
		allpurchasce();
		
	}

	private static void allpurchasce() throws Exception {

		CustomerFacade customerFacade = new CustomerFacade();
		customerFacade.customer.setCust_name("dvir");

		customerFacade.getAllPurchasedCoupon();
	}

	private static void purchasce() throws Throwable {

		CustomerDBDAO customerDBDAO=new CustomerDBDAO();
		customerDBDAO.customer.setCust_name("dvir");
		customerDBDAO.customer.setId(customerDBDAO.getCustomerByName("dvir").getId());
		
		DataVallidation dv = new DataVallidation();
		dv.customer.setCust_name("dvir");
		dv.customer.setId(customerDBDAO.getCustomerByName("dvir").getId());

		CustomerFacade customerFacade = new CustomerFacade();
		customerFacade.customer.setCust_name("dvir");
		
		
		Coupon coupon = new Coupon();
		coupon.setId((long) 5);
		customerFacade.purchasceCoupon(coupon);
	}

	private static void login() throws Exception {
		CustomerDBDAO custDao = new CustomerDBDAO();
		custDao.login("Aya", "123");
		
	}

	private static void allcust() throws SQLException {

		CustomerDBDAO cdd=new CustomerDBDAO();
		
		ArrayList<Customer> arr=(ArrayList<Customer>) cdd.getAllCustomers();
		for (int i=0;i<arr.size();i++) {
			System.out.println(arr.get(i));
		}
	}

	public static void create() throws Exception {
		CustomerDBDAO custDao = new CustomerDBDAO();
		Customer customer=new Customer();
	
		customer.setCust_name("zivziv");
		customer.setPassword("123123");
		
		custDao.createCustomer(customer);

	}

	public static void remove() throws SQLException {
		Customer cust2 = new Customer( "Dvir", "QAZWSX12");
		CustomerDBDAO custDao = new CustomerDBDAO();
		custDao.removeCustomer(cust2);
	}
	
	public static void update() throws SQLException {
		Customer cust1 = new Customer( "Aya", "12345");
		CustomerDBDAO custDao = new CustomerDBDAO();
		long id=12;
		cust1.setId(id);
		custDao.updateCustomer(cust1);

	}
}
