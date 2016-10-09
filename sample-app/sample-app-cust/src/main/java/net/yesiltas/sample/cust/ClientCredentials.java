package net.yesiltas.sample.cust;

/**
 * @see ClientCredentialsContainer
 * 
 *      POJO class used to contain client credentials for the http basic
 *      authentication
 * 
 * @author Meltem
 *
 */
public class ClientCredentials {

	private String name;
	private String secret;


	/**
	 * @return name of the client
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            name of the client
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return secret key for the client
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret
	 *            secret key for the client
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
}
