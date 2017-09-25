package com.project.Tests;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.Beans.Customer;
import com.project.Dao.Impl.CustomerDBDAO;

public class CustomerDBDAOTest {

	public static void main(String[] args) throws Exception {
		// create();
		// remove();
		//update();
		//allcust();
		login();
		
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
