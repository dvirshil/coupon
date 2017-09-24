package com.project.coupon;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.project.Dao.Impl.CompanyDBDAO;
import com.project.Dao.Impl.CustomerDBDAO;
import com.project.Facade.AdminFacade;
import com.project.Facade.ClientType;

@Path("/login")
public class LoginController {
	CompanyDBDAO companyDBDAO=new CompanyDBDAO();
	CustomerDBDAO customerDBDAO=new CustomerDBDAO();
	AdminFacade adminFacade=new AdminFacade();
	String name;
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("username") String username ,
						@FormParam("password") String passwors, 
						@FormParam("type") String type) throws Exception {

		ClientType clientType=ClientType.valueOf(type);
		
		switch (clientType) {
		 case ADMIN:
			 if(adminFacade.login(username, passwors, clientType) != null) {
				//TODO redirect to admin.html	 
				 name=username;
			 System.out.println("admin");
			 }
		 return "admin" ;
		
		 case COMPANY:
			 if(companyDBDAO.login(username, passwors)) {
				 System.out.println("company");
				 //TODO redirect to company.html
			 }
			 name=username;
		 return "company";
		
		 case CUSTOMER:
			 if(customerDBDAO.login(username, username)) {
				//TODO redirect to customer.html
				 System.out.println("customer");
			 }
			 name=username;
		 return "customer";

		 default:
		 return "WRONG";
		 }
	}
	
}
