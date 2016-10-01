package net.yesiltas.sample.cust.repository;

import org.springframework.data.repository.CrudRepository;

import net.yesiltas.sample.cust.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
