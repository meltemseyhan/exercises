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

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
    @RequestMapping(value="/customer/{id}", method=RequestMethod.GET)
    ResponseEntity<Customer> get(@PathVariable Long id) {
    	return ResponseEntity.ok().body(customerService.findOne(id));
    }	
	
    @RequestMapping(value="/customers", method=RequestMethod.GET)
    ResponseEntity<Iterable<Customer>> getAll() {
    	return ResponseEntity.ok().body(customerService.findAll());
    }	  
    
    @RequestMapping(value="/customer", method=RequestMethod.POST)
    ResponseEntity<Customer> save(@RequestBody Customer customer) {
    	return ResponseEntity.ok().body(customerService.save(customer));
    }    
    
    @RequestMapping(value="/customer/{id}", method=RequestMethod.DELETE)
    ResponseEntity<Customer> delete(@PathVariable Long id) {
    	customerService.delete(id);
    	return ResponseEntity.ok().body(null);
    }     
}
