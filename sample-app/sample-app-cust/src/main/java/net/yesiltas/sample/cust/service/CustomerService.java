package net.yesiltas.sample.cust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.yesiltas.sample.cust.model.Customer;
import net.yesiltas.sample.cust.repository.CustomerRepository;

/**
 * Class for providing all services for {@link Customer} entity
 * 
 * @author Meltem
 *
 */
@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * Finds all customers
	 * 
	 * @return All customers
	 */
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	/**
	 * Finds the customer with the given id
	 * 
	 * @param id
	 *            id of customer to find
	 * @return customer found, or else null
	 */

	public Customer findOne(Long id) {
		return customerRepository.findOne(id);
	}

	/**
	 * Inserts or updates given customer
	 * 
	 * @param cust
	 *            customer entity to be saved
	 * @return customer entity saved
	 */
	@Transactional
	public Customer save(Customer cust) {
		return customerRepository.save(cust);
	}

	/**
	 * Deletes the customer with the given id
	 * 
	 * @param id
	 *            id of customer to delete
	 */
	@Transactional
	public void delete(Long id) {
		customerRepository.delete(id);
	}
}
