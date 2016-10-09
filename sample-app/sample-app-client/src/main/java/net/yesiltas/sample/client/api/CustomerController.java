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

/**
 * RESTFUL controller class to call customer CRUD services from "cust" module
 * and serve them to client.
 * 
 * @author Meltem
 *
 */

@RestController
public class CustomerController {

	/** Factory to get the adapter for module */
	@Autowired
	ClientAdapterFactory adapterFactory;

	/**
	 * Read a specific customer using id
	 * 
	 * @param id
	 *            id of the customer to get
	 * @return the http response having customer as body
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	ResponseEntity<Customer> get(@PathVariable Long id) {
		ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
		Map<String, Long> uriVars = new HashMap<>();
		uriVars.put("id", id);
		return adapter.callService("/customer/{id}", null, Customer.class, HttpMethod.GET, uriVars);

	}

	/**
	 * List of all customers
	 * 
	 * @return the http response having list of customers as body
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	ResponseEntity<List> getAll() {
		ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
		return adapter.callService("/customers", null, List.class, HttpMethod.GET);
	}

	/**
	 * Insert or update given customer
	 * 
	 * @param customer
	 *            customer info to save
	 * @return the http response having saved customer as body
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	ResponseEntity<Customer> save(@RequestBody Customer customer) {
		ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
		return adapter.callService("/customer", customer, Customer.class, HttpMethod.POST);
	}

	/**
	 * Delete customer with the given id
	 * 
	 * @param id
	 *            id of customer to delete
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable Long id) {
		ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
		Map<String, Long> uriVars = new HashMap<>();
		uriVars.put("id", id);
		adapter.callService("/customer/{id}", null, Customer.class, HttpMethod.DELETE, uriVars);
	}
}
