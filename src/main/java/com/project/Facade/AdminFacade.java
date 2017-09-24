package com.project.Facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.project.Beans.Company;
import com.project.Beans.Coupon;
import com.project.Beans.Customer;
import com.project.Dao.Impl.CompanyDBDAO;
import com.project.Dao.Impl.CouponDBDAO;
import com.project.Dao.Impl.CustomerDBDAO;

public class AdminFacade implements CouponClientFacade {

	 CustomerDBDAO customerDBDAO;
	 CompanyDBDAO companyDBDAO;
	 CouponDBDAO couponDBDAO;
	
	public AdminFacade(){
		customerDBDAO= CustomerDBDAO.getInstance();
		companyDBDAO= CompanyDBDAO.getInstance();
		couponDBDAO= CouponDBDAO.getInstance();
		
	}
	
	public AdminFacade(CustomerDBDAO customerDBDAO) {
		this.customerDBDAO = customerDBDAO;
	}
	public AdminFacade( CompanyDBDAO companyDBDAO) {
		this.companyDBDAO = companyDBDAO;
	}
	
	public AdminFacade( CouponDBDAO couponDBDAO) {
		this.couponDBDAO = couponDBDAO;
	}
	
	
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		 if(clientType!=ClientType.ADMIN){
			 System.out.println("Please login with an Admin account."); 
		 	}
			if (!(name.equals("admin"))){
				System.out.println("Incorrect Username. Please try again.");
			}
			if (!(password.equals(1234))){
				System.out.println("Incorrect Password. Please try again.");
			}
				
			return new AdminFacade();	
	}
	
	public void createCompany(Company company) throws SQLException{
		boolean companyExist = false;
		// Create new Collection of all existing companies
		Collection<Company> allCompanies =  getAllCompany();
		// Check that Company does not yet exist BY NAME
		for (Company existingCompany : allCompanies) {
			if (existingCompany.getComp_name() == company.getComp_name()) {
				companyExist = true;
				break;
			}
		}
		// if newCompany does not exist - add it
		if (!companyExist)
			companyDBDAO.createCompany(company);
		else {
			// throw exception
			throw new SQLException("company already exist. try again. ");
		}
	}
	
	
	
	public void removeComany(Company company) throws SQLException{
		companyDBDAO.removeCompany(company);
		Coupon coupon=new Coupon();
		long id=company.getId();
		coupon.setId(id);
		couponDBDAO.removeCoupon(coupon);
	}
	
	public void updateCompany(Company company) throws SQLException{
		
		companyDBDAO.updateCompany(company);
	}
	
	public  Company getCompany(Long id) throws SQLException{
		return companyDBDAO.getCompany(id);
		 
		
	}
	
	public Collection<Company> getAllCompany() throws SQLException{
		Collection<Company> company=companyDBDAO.getAllCompanies();
		return company;
		
	}
	
	public void createCustomer(Customer customer) throws SQLException{
		customerDBDAO.createCustomer(customer);
		
	}
	public void removeCustomer(Customer customer) throws SQLException{
		customerDBDAO.removeCustomer(customer);
	
	}
	public void updateCustomer(Customer customer) throws Exception{
		Collection<Customer> allCustomers=getAllCustomer();
		boolean customerExist=false;
		for(Customer existingCustomer : allCustomers) {
			if(existingCustomer.getId() == customer.getId()) {
				customerExist=true;
				
				break;
			}
			}
		if(!customerExist) {
			customerDBDAO.updateCustomer(customer);
		}else {
			
		throw new Exception("Customer with id:"+customer.getId() + " dose not exist ");
		}
	}
	
	public Customer getCustomer(Long id) throws SQLException{
		return customerDBDAO.getCustomer(id);
	}
	
	public Collection<Customer> getAllCustomer() throws SQLException{
		ArrayList<Customer> customer=(ArrayList<Customer>) customerDBDAO.getAllCustomers();
		return customer;
		
	}

}


