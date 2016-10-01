package net.yesiltas.sample.cust.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.yesiltas.sample.cust.model.User;
import net.yesiltas.sample.cust.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
    @RequestMapping(value="/user/findAll", method=RequestMethod.GET)
    ResponseEntity<List<User>> findAll() {
    	return ResponseEntity.ok().body(userService.findAll());
    }
    
    @RequestMapping(value="/user/{username}", method=RequestMethod.GET)
    ResponseEntity<User> findByUsername(@PathVariable String username) {
    	User user = userService.findByUsername(username);
    	return ResponseEntity.ok().body(user);
    }    
}
