package com.project.Tests;

import java.sql.SQLException;

import com.project.Beans.Company;
import com.project.Beans.Customer;
import com.project.Dao.Impl.CompanyDBDAO;
import com.project.Dao.Impl.CouponDBDAO;
import com.project.Facade.AdminFacade;
import com.project.Facade.ClientType;

public class Test {

	public static void main(String[] args) throws Exception {
		// remove();
		// create();
		// update();
		// getcomp();
		// getAllComp();
		isTitleExsist();

		// removeCompanyFacade();
		// loginAdminFacade();
		// createCompanyFacade();
		// updatecustomeradminfacade();
		// getcustomerbyidadminfacade();

	}

	private static void isTitleExsist() throws Exception {
		CouponDBDAO coup = new CouponDBDAO();
		coup.isTitleExsist("sssss");
	}

	private static void getcustomerbyidadminfacade() throws SQLException {
		AdminFacade af = new AdminFacade();
		Customer customer = new Customer();
		System.out.println(af.getCustomer((long) 12));
		System.out.println();
	}

	private static void updatecustomeradminfacade() throws Exception {
		AdminFacade af = new AdminFacade();
		Customer customer = new Customer("zivziv", "123321");
		customer.setId((long) 1234);

		af.updateCustomer(customer);
	}

	private static void createCompanyFacade() throws SQLException {

		AdminFacade af = new AdminFacade();
		Company company = new Company("dvir1", "123456789", "a@a.com");
		af.createCompany(company);

	}

	private static void loginAdminFacade() throws Exception {
		AdminFacade af = new AdminFacade();
		af.login("admin", "1234", ClientType.ADMIN);

	}

	private static void removeCompanyFacade() throws SQLException {

		AdminFacade af = new AdminFacade();
		Company company = new Company("aaaa", "ssss", "aaaa");

		af.removeComany(company);

	}

	private static void getAllComp() throws SQLException {
		CompanyDBDAO compDao = new CompanyDBDAO();
		compDao.getAllCompanies();

	}

	private static void getcomp() throws SQLException {
		CompanyDBDAO compDao = new CompanyDBDAO();
		compDao.getCompany(1);

	}

	public static void update() throws SQLException {
		Company comp2 = new Company("aya", "98765", "ddddd@ddd.com");
		CompanyDBDAO compDao = new CompanyDBDAO();
		long id = 123456880;
		comp2.setId(id);
		compDao.updateCompany(comp2);

	}

	public static void create() throws SQLException {
		Company comp2 = new Company("2", "3", "4");
		CompanyDBDAO compDao = new CompanyDBDAO();
		long id = 4;
		comp2.setId(id);
		compDao.createCompany(comp2);

	}

	public static void remove() throws SQLException {
		// Company comp1 = new Company(123456L,"2","3","4");
		Company comp2 = new Company("2", "3", "4");
		CompanyDBDAO compDao = new CompanyDBDAO();
		compDao.removeCompany(comp2);
	}

}
