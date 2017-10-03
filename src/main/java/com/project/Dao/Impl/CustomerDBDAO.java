package com.project.Dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import com.project.Beans.Coupon;
import com.project.Beans.CouponType;
import com.project.Beans.Customer;
import com.project.Dao.ConnectionPool;
import com.project.Dao.CustomerDAO;

public class CustomerDBDAO implements CustomerDAO {
	private ConnectionPool pool;
	Customer customer = new Customer();
	Coupon coupon = new Coupon();
	Collection<Coupon> coupons=new ArrayList<>();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");


	public CustomerDBDAO() {
		pool = ConnectionPool.getInstance();
	}
	
	//singleton
	private static CustomerDBDAO instance= null;
	public static CustomerDBDAO getInstance(){
		
		if (instance == null) {
			instance = new CustomerDBDAO();
		}
		
		return instance;
	}


	@Override
	public void createCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnection();

		try {
			String query = "INSERT INTO Customer (Cust_name, Password)" + "VALUES ('"
					+ customer.getCust_name() + "','" + customer.getPassword() + "');";

			Statement st;
			st = con.createStatement();
			st.executeUpdate(query);
			System.out.println("Customer has been created");
		} catch (SQLException e) {
			throw new SQLException("Cannot create customer data into BD");
		}

		pool.returnConnection(con);

	}

	@Override
	public void removeCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnection();
		try {
			String query = "DELETE FROM Customer WHERE id=" + customer.getId() + ";";
			Statement st;
			st = con.createStatement();
			st.executeUpdate(query);
			System.out.println("customer has been deleted");
		} catch (SQLException e) {
			throw new SQLException("Cannot delete customer data into BD");
		}

		pool.returnConnection(con);

	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnection();

		try {

			String query = "UPDATE Customer SET cust_name=" +"'"+ customer.getCust_name() +"'"+ ", Password="
					+"'"+ customer.getPassword() +"'"+ " WHERE id=" + customer.getId() + ";";
			Statement st;
			st = con.createStatement();
			st.executeUpdate(query);
			System.out.println("customer has been updated");
		} catch (SQLException e) {
			throw new SQLException("Cannot update customer data into BD");
		}

		pool.returnConnection(con);

	}

	@Override
	public Customer getCustomer(long id) throws SQLException {
		Connection con = pool.getConnection();
		try {
			String query = "SELECT * FROM Customer WHERE id=" + id + ";";
			Statement st;
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				
				customer.setId(id);
				customer.setCust_name(rs.getString("cust_name"));
				customer.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new SQLException("Cannot select customer data from BD");
		}

		pool.returnConnection(con);
		return customer;

	}
	@Override
	public Customer getCustomerByName(String cust_name) throws Exception {
		Connection con = pool.getConnection();
		try {
			String query = "SELECT * FROM Customer WHERE cust_name='" + cust_name + "';";
			Statement st;
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				customer.setId(rs.getLong("id"));
				customer.setCust_name(cust_name);
				customer.setPassword(rs.getString("Password"));
			}

		} catch (SQLException e) {
			throw new SQLException("Cannot select customer data from BD");
		}
		pool.returnConnection(con);
		return customer;
	}

	@Override
	public Collection<Customer> getAllCustomers() throws SQLException {
		Connection con = pool.getConnection();
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		try {
			String query = "SELECT * FROM Customer";
			Statement st;
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				long custId = rs.getLong("id");
				customer.setId(custId);
				customer.setCust_name(rs.getString("cust_name"));
				customer.setPassword(rs.getString("Password"));
				
				allCustomers.add(customer);
			}

		} catch (SQLException e) {
			throw new SQLException("Cannot get all customers data from BD");
		}
		pool.returnConnection(con);
		System.out.println(allCustomers);
		return allCustomers;

	}

	@Override
	public Collection<Coupon> getCoupons() throws SQLException, Exception {
		Connection con = pool.getConnection();
		ArrayList<Coupon> Coupons = new ArrayList<Coupon>();
		try {
			String query = "SELECT * FROM Coupon";
			Statement st;
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				try {
					{
						String sdate=rs.getString("start_date");
						String eDate=rs.getString("end_date");
						String ct=rs.getString("type");

						coupon.setId(rs.getLong("id"));	
						coupon.setTitle(rs.getString("title"));
						Date startDate = format.parse(sdate);
						coupon.setStart_date(startDate);
						Date endDate = format.parse(eDate);
						coupon.setEnd_date(endDate);
						coupon.setAmount(rs.getInt("Amount"));
						coupon.setType(CouponType.valueOf(ct.toUpperCase()));
						coupon.setMessage(rs.getString("message"));
						coupon.setPrice(rs.getDouble("price"));
						coupon.setImage(rs.getString("image"));
						Coupons.add(coupon);
					}
				} catch (Exception e) {
					throw new Exception("Cannot select Coupons data from BD");
				}

		} catch (SQLException e) {
			throw new SQLException("Cannot select Coupons data from BD");
		}
		pool.returnConnection(con);
		return Coupons;

	}
	// the function returns collection of all the  id coupon the customer own
	public Collection<Coupon> getCouponsByCustomer(Customer customer) throws SQLException, Exception {
		Connection con = pool.getConnection();
		ArrayList<Coupon> customerCouponsID = new ArrayList<Coupon>();
		try {
			String query = "SELECT * FROM customer_coupon WHERE cust_id='" + this.customer.getId() + "';";
			Statement st;
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				try {
						
						Long coupon_id = rs.getLong("id");
						coupon.setId(coupon_id);
						customerCouponsID.add(coupon);
							
				} catch (Exception e) {
					throw new Exception("Cannot select Coupons data from BD");
				}

		} catch (SQLException e) {
			throw new SQLException("Cannot select Coupons data from BD");
		}
		pool.returnConnection(con);
		return customerCouponsID;
	}


	// updates customer_coupon table  
	public void UpdateCustomer_CouponTable(Customer customer, Coupon coupon) throws SQLException {
		Connection con = pool.getConnection();
		customer.setId((long) 123);
		coupon.setId((long) 6);
		try {
			String query = "INSERT INTO Customer_Coupon (Cust_ID, Coupon_ID)" + "VALUES' (" + customer.getId() + "','"
					+ coupon.getId() + "');";
			Statement st;
			st = con.createStatement();
			st.executeUpdate(query);
		

			System.out.println("Customer_Coupon table has been updated");
		} catch (SQLException e) {
			throw new SQLException("Cannot update customer_coupon data into DB");
			
		}

		pool.returnConnection(con);

	}

	public void RemoveCustomer_CouponTable(Coupon coupon) throws SQLException {
		Connection con = pool.getConnection();
		try {
			String query = "REMOVE *FROM Customer_Coupon WHERE id=" + coupon.getId() + ";";
			Statement st;
			st = con.createStatement();
			st.executeUpdate(query);
			System.out.println("Customer_Coupon toble has been updated");
		} catch (SQLException e) {
			throw new SQLException("Cannot update customer_coupon data into BD");
		}

		pool.returnConnection(con);

	}

	@Override
	public boolean login(String custName, String password) throws Exception {
		
		Connection con = pool.getConnection();
		boolean passwordMatchcustName=true;
		try {
			String query = "SELECT* FROM Customer WHERE cust_name='" + custName + "';";
			Statement st;
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()){
				if (rs.getString("password").equals(password)) {
					passwordMatchcustName = true;
				} else {
					passwordMatchcustName = false;
					throw new Exception(" customer: faild to log in as:" +custName +" please try again");
				}
			}
		} catch (SQLException e) {
			throw new Exception("Cannot login custName to BD: "+custName );
		}

		pool.returnConnection(con);
		return passwordMatchcustName;
	}


	@Override
	public Collection<Coupon> getCustomerCouponByCouponsId(Collection<Coupon> coupons) throws SQLException, Exception {
		for(Coupon customerCoupon: coupons) {
			CouponDBDAO couponDBDAO=new CouponDBDAO();
			couponDBDAO.getCoupon(customerCoupon.getId());
			coupons.add(customerCoupon);
		}
		
		
		return coupons;
	}


	

}
