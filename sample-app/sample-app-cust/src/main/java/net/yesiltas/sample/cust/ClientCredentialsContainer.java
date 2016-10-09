package net.yesiltas.sample.cust;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Class for containing all valid client credentials for http basic
 * authentication which are defined in application context
 * <p>
 * 
 * \@ConfigurationProperties annotation populates an instance of this class
 * automatically using the properties defined in application context (e.g.
 * application.properties)
 * <p>
 * Sample configuration: <br>
 * security.clients[0].name = "sample-app-client" <br>
 * security.clients[0].secret = "sample-app-client-secret"
 * 
 * @author Meltem
 *
 */
@Component
@ConfigurationProperties(prefix = "security")
public class ClientCredentialsContainer {

	private List<ClientCredentials> clients;

	/**
	 * @return list of all valid client credentials
	 */
	public List<ClientCredentials> getClients() {
		return clients;
	}

	/**
	 * @param clients
	 *            list of all valid client credentials
	 */
	public void setClients(List<ClientCredentials> clients) {
		this.clients = clients;
	}

}
