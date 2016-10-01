package net.yesiltas.sample.client.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.yesiltas.sample.client.adapter.ClientAdapter;
import net.yesiltas.sample.client.adapter.ClientAdapterFactory;
import net.yesiltas.sample.cust.model.Customer;

@RestController
public class CustomerController {
	
	@Autowired
	ClientAdapterFactory adapterFactory;	
	
    @RequestMapping(value="/customer/{id}", method=RequestMethod.GET)
    ResponseEntity<Customer> get(@PathVariable Long id) {
    	ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
		Map<String, Long> uriVars = new HashMap<>();
		uriVars.put("id", id);
		return adapter.callService("/customer/{id}", null, Customer.class, HttpMethod.GET, uriVars);

    }	
	
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/customers", method=RequestMethod.GET)
    ResponseEntity<List> getAll() {
    	ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
		return adapter.callService("/customers", null, List.class, HttpMethod.GET);	
    }	  
    
    @RequestMapping(value="/customer", method=RequestMethod.POST)
    ResponseEntity<Customer> save(@RequestBody Customer customer) {
    	ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
    	return adapter.callService("/customer", customer, null, HttpMethod.POST);	
    }    
    
    @RequestMapping(value="/customer/{id}", method=RequestMethod.DELETE)
    ResponseEntity<Customer> delete(@PathVariable Long id) {
    	ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
		Map<String, Long> uriVars = new HashMap<>();
		uriVars.put("id", id);
		return adapter.callService("/customer/{id}", null, Customer.class, HttpMethod.DELETE, uriVars);
    }     
}
