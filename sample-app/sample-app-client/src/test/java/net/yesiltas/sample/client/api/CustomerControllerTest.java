package net.yesiltas.sample.client.api;

import static net.yesiltas.sample.common.model.JsonUtils.toJson;
import static net.yesiltas.sample.common.model.JsonUtils.toList;
import static net.yesiltas.sample.common.model.JsonUtils.toObject;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.yesiltas.sample.client.ClientApplicationInitializer;
import net.yesiltas.sample.cust.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ClientApplicationInitializer.class })

/**
 * 
 * For testing {@link CustomerController}
 * 
 * @author Meltem
 *
 */
public class CustomerControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	/**
	 * Initializes mockMvc
	 * 
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Test all CRUD operations for customer entity. "Cust" module back-end must
	 * be running for this test to work
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		// Get the list
		String response = mockMvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse()
				.getContentAsString();

		List<Customer> customers = toList(response, Customer.class);
		int customerCount = customers.size();
		customers = null;

		// Insert a new customer, inserted customer should return
		Customer inCust = new Customer("John Simith", "john@crossover.com", "My address", "Istanbul", "Turkey");
		response = mockMvc
				.perform(post("/customer").contentType(MediaType.APPLICATION_JSON_UTF8).content(toJson(inCust)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse().getContentAsString();

		Customer outCust = toObject(response, Customer.class);
		assertEquals(inCust.getAddress(), outCust.getAddress());
		assertEquals(inCust.getCity(), outCust.getCity());
		assertEquals(inCust.getCountry(), outCust.getCountry());
		assertEquals(inCust.getEmail(), outCust.getEmail());
		assertEquals(inCust.getName(), outCust.getName());

		// Get newly added customer
		response = mockMvc.perform(get("/customer/{id}", outCust.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name").value(outCust.getName())).andReturn().getResponse().getContentAsString();

		assertEquals(outCust, toObject(response, Customer.class));

		// Get the list again, expected to have +1 customer
		response = mockMvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse()
				.getContentAsString();

		customers = toList(response, Customer.class);
		assertEquals(customerCount + 1, customers.size());

		// Update the newly added customer

		outCust.setAddress(outCust.getAddress() + "updated");
		response = mockMvc
				.perform(post("/customer").contentType(MediaType.APPLICATION_JSON_UTF8).content(toJson(outCust)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn().getResponse().getContentAsString();

		Customer updatedCust = toObject(response, Customer.class);
		assertEquals(outCust.getId(), updatedCust.getId());
		assertEquals(outCust.getAddress(), updatedCust.getAddress());
		assertEquals(outCust.getCity(), updatedCust.getCity());
		assertEquals(outCust.getCountry(), updatedCust.getCountry());
		assertEquals(outCust.getEmail(), updatedCust.getEmail());
		assertEquals(outCust.getName(), updatedCust.getName());
		updatedCust = null;

		// Delete the newly added customer
		mockMvc.perform(delete("/customer/{id}", outCust.getId())).andExpect(status().isOk());

		// Get newly deleted customer, it should not exist
		mockMvc.perform(get("/customer/{id}", outCust.getId())).andExpect(status().isOk())
				.andExpect(content().string(""));

		// Get the list again, expected to have -1 customer
		response = mockMvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse()
				.getContentAsString();

		customers = toList(response, Customer.class);
		assertEquals(customerCount, customers.size());
	}
}
