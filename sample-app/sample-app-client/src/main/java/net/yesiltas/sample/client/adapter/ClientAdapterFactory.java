package net.yesiltas.sample.client.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Client project can depend several back-ends to be served with necessary
 * business logic.
 * 
 * Each back-end can be implemented as a separate and independent project which
 * serve to Client project. Instead of a single monolithic application, this way
 * of implementation is preferred to honor micro-service architecture.
 * 
 * Each of the back-end projects can have different authentication requirements,
 * some of them even may require a custom header for some purposes.
 * 
 * To support communication with different backend types, different
 * ClientAdapter classes may be implemented.
 * 
 * For example a Client Adapter to call RESTFUL services with Basic Http
 * Authentication ({@link BasicAuthRestAdapter}) is already implemented.
 * 
 * In application context each backend is defined as a module, and the adapter
 * class to use is specified (e.g. modules.cust.adapter.class =
 * net.yesiltas.sample.client.adapter.BasicAuthRestAdapter)
 * 
 * This factory class is used to instantiate and initialize defined Adapter for
 * the given module name.
 * 
 * Since this is a factory class, its scope is singleton.
 * 
 * @author Meltem
 */

@Component
@Scope("singleton")
public class ClientAdapterFactory {
	private static final Logger logger = LoggerFactory.getLogger(ClientAdapterFactory.class);

	/**
	 * Spring's environment object to access properties in application context
	 * (e.g. application-xxxx.xml)
	 */
	@Autowired
	private Environment env;

	private ClientAdapterFactory() {
		super();
	}

	/**
	 * Instantiate and initialize defined Adapter for the given module name
	 * 
	 * @param moduleName
	 *            name of module
	 * @return ClientAdapter instance defined for the given module name, null if
	 *         there is an exception during initialization
	 */
	public ClientAdapter getAdapterForModule(String moduleName) {
		try {
			ClientAdapter adapter = (ClientAdapter) Class
					.forName(env.getProperty("modules." + moduleName + ".adapter.class")).newInstance();
			adapter.setModuleName(moduleName, env);
			return adapter;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

}
