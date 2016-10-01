package net.yesiltas.sample.cust.model;

import javax.persistence.Entity;

import net.yesiltas.sample.common.model.BaseEntity;

@Entity
public class Customer extends BaseEntity {

	private String name;
	
	private String email;
	
	private String address;
	
	private String city;	
	
	private String country;
	
	public Customer(String name, String email, String address, String city, String country){
		this.name = name;
		this.city = city;
		this.address = address;
		this.country = country;
		this.email = email;
	}
	
	public Customer(){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
