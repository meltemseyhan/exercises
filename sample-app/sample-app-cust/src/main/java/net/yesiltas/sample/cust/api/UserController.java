package net.yesiltas.sample.cust.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.yesiltas.sample.cust.model.User;
import net.yesiltas.sample.cust.service.UserService;

/**
 * RESTFUL controller class to call customer CRUD services for {@link User}
 * entity
 * 
 * @author Meltem
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * Get the user with given id
	 * 
	 * @param id
	 *            id of user to get
	 * @return the http response having user as body
	 */    
	
    @RequestMapping(value="/user/{username}", method=RequestMethod.GET)
    ResponseEntity<User> findByUsername(@PathVariable String username) {
    	User user = userService.findByUsername(username);
    	return ResponseEntity.ok().body(user);
    }    
}
