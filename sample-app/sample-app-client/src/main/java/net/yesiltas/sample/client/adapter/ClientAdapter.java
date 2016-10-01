package net.yesiltas.sample.client.adapter;

import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface ClientAdapter { //NOSONAR
	
	static String MODULES_PREFIX = "modules.";
	
	void setModuleName(String moduleName, Environment env);
	
	<T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType);
	
	<T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType, HttpMethod httpMethod);
	
	<T> ResponseEntity<T> callService(String uri, Object requestBody, Class<T> responseType, HttpMethod httpMethod, Map<String,?> uriVariables);
}
