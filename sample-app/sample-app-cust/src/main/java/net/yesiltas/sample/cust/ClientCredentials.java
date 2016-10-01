package net.yesiltas.sample.cust;

public class ClientCredentials {
	
	private String name;
	private String secret;
	
	public ClientCredentials(){
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
}
