package net.yesiltas.sample.client.adapter;

import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 * @see ClientAdapterFactory
 * 
 *      An adapter is used to talk to a back-end. ClientAdapterFactory assumes
 *      that all the adapters classes implement this interface.
 * 
 * @author Meltem
 */

public interface ClientAdapter { // NOSONAR

	static String MODULES_PREFIX = "modules.";

	/**
	 * This method is called by {@link ClientAdapterFactory} to initialize
	 * adapter.
	 * 
	 * @param moduleName
	 *            name of module
	 * @param env
	 *            Spring's environment object to read properties in application
	 *            context
	 */
	void setModuleName(String moduleName, Environment env);

	/**
	 * Calls a service from the module back-end
	 * 
	 * @param uri
	 *            service path, which is appended to apiUrl
	 * @param requestBody
	 *            object to send as request payload
	 * @param responseType
	 *            expected type of the return object
	 * 
	 * @return http response
	 * 
	 */
	<T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType);

	/**
	 * Calls a service from the module back-end using specified method
	 * 
	 * @param uri
	 *            service path, which is appended to apiUrl
	 * @param requestBody
	 *            object to send as request payload
	 * @param responseType
	 *            expected type of the return object
	 * @param httpMethod
	 *            http method to use
	 * 
	 * @return http response
	 * 
	 */
	<T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType, HttpMethod httpMethod);

	/**
	 * Calls a service from the module back-end with uri variables using
	 * specified method
	 * 
	 * @param uri
	 *            service path, which is appended to apiUrl
	 * @param requestBody
	 *            object to send as request payload
	 * @param responseType
	 *            expected type of the return object
	 * @param httpMethod
	 *            http method to use
	 * @param uriVariables
	 *            uri variables
	 * 
	 * @return http response
	 * 
	 */
	<T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType, HttpMethod httpMethod,
			Map<String, ?> uriVariables);
}
