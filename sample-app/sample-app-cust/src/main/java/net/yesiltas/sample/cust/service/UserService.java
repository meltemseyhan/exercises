package net.yesiltas.sample.cust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yesiltas.sample.cust.model.User;
import net.yesiltas.sample.cust.repository.UserRepository;

/**
 * Class for providing all services for {@link User} entity
 * 
 * @author Meltem
 *
 */

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * Finds the user with the given username
	 * 
	 * @param username
	 *            username of user to find
	 * @return user found, or else null
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Finds all users
	 * 
	 * @return All users
	 */
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
}
