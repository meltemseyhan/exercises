package net.yesiltas.sample.cust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yesiltas.sample.cust.model.Customer;
import net.yesiltas.sample.cust.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer findByUsername(String name){
		return customerRepository.findByName(name);
	}
	
	public Iterable<Customer> findAll(){
		return customerRepository.findAll();
	}	
	
	public Customer findOne(Long id){
		return customerRepository.findOne(id);
	}	
	
	public Customer save(Customer cust){
		return customerRepository.save(cust);
	}	
	
	public void delete(Long id) {
		customerRepository.delete(id);
	}		
}
