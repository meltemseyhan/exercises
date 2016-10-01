package net.yesiltas.sample.cust.repository;

import org.springframework.data.repository.CrudRepository;

import net.yesiltas.sample.cust.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findByName(String name);
}
