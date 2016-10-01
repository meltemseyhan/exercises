package net.yesiltas.sample.cust;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security")
public class ClientCredentialsContainer {
	
	private List<ClientCredentials> clients;

	public List<ClientCredentials> getClients() {
		return clients;
	}

	public void setClients(List<ClientCredentials> clients) {
		this.clients = clients;
	}

}
