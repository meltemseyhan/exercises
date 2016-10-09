package net.yesiltas.sample.cust.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.yesiltas.sample.cust.CustApplicationInitializer;
import net.yesiltas.sample.cust.model.Customer;

/**
 * For testing {@link CustomerService}
 * 
 * @author Meltem
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustApplicationInitializer.class })
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;

	/**
	 * Tests all customer services
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	@Test
	public void test() throws IOException {

		Iterable<Customer> list = customerService.findAll();
		int origCount = 0;

		for (Customer customer : list) {
			origCount++;
		}

		Customer in = new Customer("Meltem", "meltem@yesiltas.net", "my address", "my city", "my country");
		Customer out = customerService.save(in);
		assertEquals(in.getCountry(), out.getCountry());
		assertEquals(in.getAddress(), out.getAddress());
		assertEquals(in.getCity(), out.getCity());
		assertEquals(in.getEmail(), out.getEmail());
		assertEquals(in.getName(), out.getName());

		list = customerService.findAll();
		int newCount = 0;
		for (Customer customer : list) {
			newCount++;
		}
		assertEquals(origCount + 1, newCount);

		Customer out2 = customerService.findOne(out.getId());
		assertEquals(out, out2);

		out.setAddress(out.getAddress() + "updated");
		customerService.save(out);

		list = customerService.findAll();
		newCount = 0;
		for (Customer customer : list) {
			newCount++;
		}
		assertEquals(origCount + 1, newCount);

		customerService.delete(out.getId());

		list = customerService.findAll();
		newCount = 0;
		for (Customer customer : list) {
			newCount++;
		}
		assertEquals(origCount, newCount);

	}

}
