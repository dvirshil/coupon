package com.project.coupon;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.project.Dao.Impl.CompanyDBDAO;
import com.project.Dao.Impl.CustomerDBDAO;
import com.project.Facade.AdminFacade;
import com.project.Facade.ClientType;

@Singleton
@Path("/login")
public class LoginController {
	@Context    
	private HttpServletResponse response;
	
	CompanyDBDAO companyDBDAO=new CompanyDBDAO();
	CustomerDBDAO customerDBDAO=new CustomerDBDAO();
	AdminFacade adminFacade=new AdminFacade();
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public void login(@FormParam("username") String username ,
						@FormParam("password") String passwors, 
						@FormParam("type") String type) throws Exception {

		ClientType clientType=ClientType.valueOf(type.toUpperCase());
		
		switch (clientType) {
		 case ADMIN:
			 if(adminFacade.login(username, passwors, clientType) != null) {
				 response.sendRedirect("/coupon/admin.html");
			 }break;
		 
		 case COMPANY:
			 if(companyDBDAO.login(username, passwors)) {
				 response.sendRedirect("/coupon/company.html?username="+username);
			 }break;
		 
		 case CUSTOMER:
			 if(customerDBDAO.login(username, passwors)) {
				 response.sendRedirect("/coupon/customer.html?username="+username);
			 }break;
		}
	}	
}
