package net.yesiltas.sample.cust.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.yesiltas.sample.common.model.SampleResponse;
import net.yesiltas.sample.cust.model.User;
import net.yesiltas.sample.cust.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	UserService userService;
	
    @RequestMapping(value="/cust/api/{username}/login", method=RequestMethod.POST)
    SampleResponse login(@PathVariable String username, @RequestBody HashMap<String, String> request) {
    	SampleResponse resp;
    	User user = userService.findByUsername(username);
    	if (user == null) {
    		resp = new SampleResponse(false, "Username does not exist", null);
		} else if (user.getPassword().equals(request.get("password"))) {
			resp = new SampleResponse(true, "", null);
		} else {
			resp = new SampleResponse(false, "Password is wrong", null);
		}
        return resp;
    }

}
