package net.yesiltas.sample.cust.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.yesiltas.sample.cust.model.Customer;
import net.yesiltas.sample.cust.service.CustomerService;

/**
 * RESTFUL controller class to call customer CRUD services for {@link Customer}
 * entity
 * 
 * @author Meltem
 */
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * Get the customer with given id
	 * 
	 * @param id
	 *            id of customer to get
	 * @return the http response having customer as body
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	ResponseEntity<Customer> get(@PathVariable Long id) {
		return ResponseEntity.ok().body(customerService.findOne(id));
	}

	/**
	 * List of all customers
	 * 
	 * @return the http response having list of customers as body
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	ResponseEntity<Iterable<Customer>> getAll() {
		return ResponseEntity.ok().body(customerService.findAll());
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
		return ResponseEntity.ok().body(customerService.save(customer));
	}

	/**
	 * Delete customer with the given id
	 * 
	 * @param id
	 *            id of customer to delete
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable Long id) {
		customerService.delete(id);
	}
}
