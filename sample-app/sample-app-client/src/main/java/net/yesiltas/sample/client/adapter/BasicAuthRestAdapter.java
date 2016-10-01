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

@Component
public class BasicAuthRestAdapter implements ClientAdapter {
	

	private String apiUrl;
	
	private String authClientId;
	
	private String authClientSecret;


	public BasicAuthRestAdapter() {
		super();
	}

	@Override
	public void setModuleName(String moduleName, Environment env) {
		this.apiUrl = env.getProperty(MODULES_PREFIX + moduleName + ".api.url");
		this.authClientId = env.getProperty(MODULES_PREFIX + moduleName + ".auth.client.id");
		this.authClientSecret = env.getProperty(MODULES_PREFIX + moduleName + ".auth.client.secret");
	}
	
	@Override
	public <T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType){
		return this.callService(uri, requestBody, responseType, HttpMethod.POST, new HashMap<>());
	}
	
	@Override
	public <T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType, HttpMethod httpMethod){
		return this.callService(uri, requestBody, responseType, httpMethod, new HashMap<>());
	}	
	
	@Override
	public <T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType, HttpMethod httpMethod, Map<String,?> uriVariables){
        String plainCredentials=this.authClientId + ":" + this.authClientSecret;
        String base64Credentials = new String(Base64.encode(plainCredentials.getBytes()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));			
        headers.add("Authorization", "Basic " + base64Credentials);
		
		HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(apiUrl + uri, (httpMethod == null) ? HttpMethod.POST: httpMethod, request, responseType, (uriVariables == null) ? new HashMap<>(): uriVariables);
	}	

}