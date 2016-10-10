package net.yesiltas.sample.cust.model;

import javax.persistence.Entity;

import net.yesiltas.sample.common.model.BaseEntity;

/**
 * Entity class for Customer
 * 
 * @author Meltem
 *
 */
@Entity
public class Customer extends BaseEntity {

	private String name;

	private String email;

	private String address;

	private String city;

	private String country;

	/**
	 * Default constructor
	 */
	public Customer() {
		super();
	}

	/**
	 * Constructs a customer with the given parameters
	 * 
	 * @param name
	 *            name of customer
	 * @param email
	 *            email of customer
	 * @param address
	 *            address of customer
	 * @param city
	 *            city of customer
	 * @param country
	 *            country of customer
	 */
	public Customer(String name, String email, String address, String city, String country) {
		this.name = name;
		this.city = city;
		this.address = address;
		this.country = country;
		this.email = email;
	}

	/**
	 * 
	 * @return name of customer
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            name of customer
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return email of customer
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 *            email of customer
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return address of customer
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address
	 *            address of customer
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @return city of customer
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city
	 *            city of customer
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return country of customer
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @param country
	 *            country of customer
	 */
	public void setCountry(String country) {
		this.country = country;
	}
}
