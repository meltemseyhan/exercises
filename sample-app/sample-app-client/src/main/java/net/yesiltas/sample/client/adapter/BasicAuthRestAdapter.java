package net.yesiltas.sample.client.adapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This is a concrete implementation of {@link ClientAdapter}, it is used for
 * back-ends which are RESTFUL and require Http Basic Authentication
 * 
 * @author Meltem
 *
 */

@Component
public class BasicAuthRestAdapter implements ClientAdapter {

	/** Base URL to call services */
	private String apiUrl;

	/** Client id to use for HTTP Basic Auth */
	private String authClientId;

	/** Client secret to use for HTTP Basic Auth */
	private String authClientSecret;

	/**
	 * This method is called by {@link ClientAdapterFactory} to initialize
	 * adapter.
	 * 
	 * @see net.yesiltas.sample.client.adapter.ClientAdapter#setModuleName(java.lang.String,
	 *      org.springframework.core.env.Environment)
	 * 
	 * @param moduleName
	 *            name of module
	 * @param env
	 *            Spring's environment object to read properties in application
	 *            context
	 */
	@Override
	public void setModuleName(String moduleName, Environment env) {
		this.apiUrl = env.getProperty(MODULES_PREFIX + moduleName + ".api.url");
		if (!apiUrl.endsWith("/")) {
			apiUrl += "/";
		}
		this.authClientId = env.getProperty(MODULES_PREFIX + moduleName + ".auth.client.id");
		this.authClientSecret = env.getProperty(MODULES_PREFIX + moduleName + ".auth.client.secret");
	}

	/**
	 * Calls a service from the module back-end using POST method with no uri
	 * variables
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
	@Override
	public <T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType) {
		return this.callService(uri, requestBody, responseType, HttpMethod.POST, new HashMap<>());
	}

	/**
	 * Calls a service from the module back-end using specified method with no
	 * uri variables
	 * 
	 * @param uri
	 *            service path, which is appended to apiUrl
	 * @param requestBody
	 *            object to send as request payload
	 * @param responseType
	 *            expected type of the return object
	 * @param httpMethod
	 *            http method to use, POST is default
	 * 
	 * @return http response
	 * 
	 */
	@Override
	public <T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType,
			HttpMethod httpMethod) {
		return this.callService(uri, requestBody, responseType, httpMethod, new HashMap<>());
	}

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
	 *            http method to use, POST is default
	 * @param uriVariables
	 *            uri variables
	 * 
	 * @return http response
	 * 
	 */
	@Override
	public <T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType,
			HttpMethod httpMethod, Map<String, ?> uriVariables) {
		String plainCredentials = this.authClientId + ":" + this.authClientSecret;
		String base64Credentials = new String(Base64.encode(plainCredentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + base64Credentials);

		HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(apiUrl + uri, (httpMethod == null) ? HttpMethod.POST : httpMethod, request,
				responseType, (uriVariables == null) ? new HashMap<>() : uriVariables);
	}

}