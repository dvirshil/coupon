package com.project.coupon;


import java.sql.SQLException;
import java.util.Collection;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.project.Beans.Company;
import com.project.Beans.Customer;
import com.project.Facade.AdminFacade;

@Path("/admin")
@Singleton
public class AdminController {
	
	
	@Context    
	private HttpServletResponse response;
	
	 AdminFacade adminFacade=new AdminFacade();
	 Company company=new Company();
	 Customer customer=new Customer();
	
	@POST
	@Path("/createCompany")
	@Produces(MediaType.APPLICATION_JSON)
	public void createCompanyTest(@FormParam("companyName") String companyName,
									@FormParam("companyPassword") String companyPassword,
									@FormParam("companyEmail") String companyEmail) throws Exception {
							
		
		
		company.setComp_name(companyName);
		company.setPassword(companyPassword);
		company.setEmail(companyEmail);
			adminFacade.createCompany(company);
		
		response.sendRedirect("/coupon/admin.html");
		//return "create company - admin.html";
	}
	
	
	
	@POST
	@Path("/removeCompany")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCompany(@FormParam("companyId") String companyId) throws SQLException {
		
		long id=Long.parseLong(companyId);
		company.setId(id);
		adminFacade.removeComany(company);
		
		return "remove company - admin.html";
		
	}
	
	@POST
	@Path("/updateCompany")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCompany(@FormParam("companyId") String companyId,
								@FormParam("companyName") String companyName,
								@FormParam("companyPsaaword") String companyPassword,
								@FormParam("companyEmail") String companyEmail) throws Exception {
		
		long id=Long.parseLong(companyId);
		company.setId(id);		
		company.setComp_name(companyName);
		company.setPassword(companyPassword);
		company.setEmail(companyEmail);
		
		adminFacade.updateCompany(company);
		
		return "update company - admin.html";
	}
	
	@POST
	@Path("/getCompanyById")
	@Produces(MediaType.APPLICATION_JSON)
	public Company getConpantById(@FormParam("companyId") String companyId) throws Exception {
		long id=Long.parseLong(companyId);
		try {
			
			adminFacade.getCompany(id);
			company.setId(id);
			company.setComp_name(adminFacade.getCompany(id).getComp_name());
			company.setPassword(adminFacade.getCompany(id).getPassword());
			company.setEmail(adminFacade.getCompany(id).getEmail());

		} catch (Exception e) {
			throw new Exception("FAILED GET COMPANY");
		}
		response.sendRedirect("admin.html");
		return company;
	}
	
	@GET
	@Path("/getAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Company> getAllCompanies() throws Exception{
		
		Collection<Company> companies = null;
		try {
			adminFacade.getAllCompany();
		} catch (Exception e) {
			throw new Exception("FAILED GET ALL COMPANIES");
		}
		return companies;
	}
	
	
	@POST
	@Path("/createCustomer")
	@Produces(MediaType.TEXT_PLAIN)
	public String createCustomer(@FormParam("customerName") String customerName,
								@FormParam("customerPasswors") String customerPassword) throws SQLException  {
		customer.setCust_name(customerName);
		customer.setPassword(customerPassword);
		adminFacade.createCustomer(customer);
		
		return "create customer - admin.html";
	}
	
	@POST
	@Path("/removeCustomer")
	public String removeCustomer(@FormParam("customerId") long customerId) throws Exception {
		
		customer.setId(customerId);
		adminFacade.removeCustomer(customer);
		
		return "remove customer - admin.html";
	}
	
	@POST
	@Path("/updateCustomer")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(@FormParam("customerId") long customerId,
								@FormParam("customerName") String customerName, 
								@FormParam("customerPassword") String customerPassword) throws Exception  {
			
		
					customer.setId(customerId);
					customer.setCust_name(customerName);
					customer.setPassword(customerPassword);
					adminFacade.updateCustomer(customer);
		
					return "update customer - admin.html";
			}
		
	
	
	@POST
	@Path("/getCustomer")
	@Produces(MediaType.TEXT_PLAIN)
	public Customer getCustomer(@FormParam("customerId") long customerId) throws Exception {
		
		adminFacade.getCustomer(customerId);
		customer.setId(customerId);
		customer.setCust_name(adminFacade.getCustomer(customerId).getCust_name());
		customer.setPassword(adminFacade.getCustomer(customerId).getPassword());
		System.out.println(customer);
		System.out.println("get customer - admin.html");
		return customer;
	}
	
	@GET
	@Path("/getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getAllCustomers() throws Exception{
		
		Collection<Customer> customers = null;
		adminFacade.getAllCustomer();
		
		System.out.println("get all customers - admin.html");
				return customers;
	}
}

