package net.yesiltas.sample.cust.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.yesiltas.sample.cust.CustApplicationInitializer;
import net.yesiltas.sample.cust.model.User;

/**
 * For testing {@link UserService}
 * 
 * @author Meltem
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustApplicationInitializer.class })
public class UserServiceTest {

	@Autowired
	UserService userService;

	/**
	 * Tests all user services
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		
		User user = userService.findByUsername("meltem");
		assertEquals("meltem@yesiltas.net", user.getEmail());
		
		Iterable<User> list = userService.findAll();
		boolean found = false;
		for (User next : list) {
			if(next.equals(user)){
				found = true;
				break;
			}
		}
		assertEquals(true, found);

	}

}
