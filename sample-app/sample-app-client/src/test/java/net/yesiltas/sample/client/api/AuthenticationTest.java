package net.yesiltas.sample.client.api;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.yesiltas.sample.client.ClientApplicationInitializer;
import net.yesiltas.sample.client.WebSecurityConfig;

/**
 * 
 * For testing Spring Security form login configuration
 * 
 * @author Meltem
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ClientApplicationInitializer.class, WebSecurityConfig.class })
public class AuthenticationTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	/**
	 * Initializes mockMvc, ".apply(springSecurity())" must be called to be able
	 * to test security
	 * 
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	/**
	 * Tests form login with correct credentials and incorrect credentials
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		// Login with wrong credentials, expect 401
		mockMvc.perform(formLogin("/cust/login").user("meltemmm").password("passworddd")).andExpect(status().is(401));

		// Login with correct credentials, expect 200
		mockMvc.perform(formLogin("/cust/login").user("meltem").password("password")).andExpect(status().isOk());

	}
}
