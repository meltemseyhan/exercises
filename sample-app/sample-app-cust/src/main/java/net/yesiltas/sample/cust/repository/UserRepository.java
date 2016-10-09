package net.yesiltas.sample.cust.repository;

import org.springframework.data.repository.CrudRepository;
import net.yesiltas.sample.cust.model.User;

/**
 * CRUD repository for {@link User} entity
 * 
 * @author Meltem
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	/**
	 * Find user with username
	 * 
	 * @param username
	 *            username to find
	 * @return user found, or else null
	 */
	User findByUsername(String username);
}
