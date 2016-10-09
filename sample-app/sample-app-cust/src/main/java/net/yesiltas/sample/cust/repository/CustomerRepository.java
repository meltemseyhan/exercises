package net.yesiltas.sample.cust.repository;

import org.springframework.data.repository.CrudRepository;

import net.yesiltas.sample.cust.model.Customer;

/**
 * CRUD repository for {@link Customer} entity
 * 
 * @author Meltem
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
