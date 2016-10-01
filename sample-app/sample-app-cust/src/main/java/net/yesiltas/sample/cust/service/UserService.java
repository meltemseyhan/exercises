package net.yesiltas.sample.cust.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yesiltas.sample.cust.model.User;
import net.yesiltas.sample.cust.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}
}
