package com.project.Beans;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Customer {
	private Long id;
	private String cust_name;
	private String password;
	private Collection<Coupon>coupons;
	
	//constructor
	public Customer(){
		
	}
	
	public Customer( String cust_name,String Password){
		
		this.cust_name=cust_name;
		this.password= Password;

		}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCust_name() {
		return cust_name;
	}
	
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	@Override
	public String toString(){
		return "Customer : id="+ id +", name="+ cust_name + ", password=" + password;
		
	}

}
