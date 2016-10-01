package net.yesiltas.sample.cust.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<User> findAll(){
		List<User> list = new ArrayList<>();
		Iterable<User> iter = userRepository.findAll();
		while (iter.iterator().hasNext()) {
			User user = iter.iterator().next();
			list.add(user);			
		}
		return list;
	}	
}
