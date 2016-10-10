package net.yesiltas.sample.cust.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import net.yesiltas.sample.common.model.BaseEntity;

/**
 * Entity class for User
 * 
 * @author Meltem
 *
 */
@Entity
public class User extends BaseEntity { // NOSONAR

	private String firstName;
	private String lastName;
	private String email;

	@Column(unique = true)
	private String username;

	private String password;

	private Boolean locked = false;

	private Boolean expired = false;

	private Boolean enabled = true;

	private Boolean credentialsExpired = false;

	/**
	 * Default constructor
	 */
	public User() {
		super();
	}

	/**
	 * Constructs a user object with the given parameters
	 * 
	 * @param firstName
	 *            first name of the user
	 * @param lastName
	 *            last name of the user
	 * @param username
	 *            username of the user
	 * @param password
	 *            password of the user
	 * @param email
	 *            email of the user
	 */
	public User(String firstName, String lastName, String username, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/**
	 * 
	 * @return first name of user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            first name of user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return last name of user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            last name of user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return email of user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 *            email of user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return username of user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 *            username of user
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 *            password of user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return whether user is locked
	 */
	public Boolean getLocked() {
		return locked;
	}

	/**
	 * 
	 * @param locked
	 *            whether user is locked
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	/**
	 * 
	 * @return whether user is expired
	 */
	public Boolean getExpired() {
		return expired;
	}

	/**
	 * 
	 * @param expired
	 *            whether user is expired
	 */
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	/**
	 * 
	 * @return whether user is enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * 
	 * @param enabled
	 *            whether user is enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 
	 * @return whether user's credentials are expired
	 */
	public Boolean getCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * 
	 * @param credentialsExpired
	 *            whether user's credentials are expired
	 */
	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

}