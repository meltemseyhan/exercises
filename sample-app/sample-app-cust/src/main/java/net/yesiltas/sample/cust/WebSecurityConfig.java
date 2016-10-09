package net.yesiltas.sample.cust;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * This class is for enabling Spring Security. It provides necessary
 * configuration for basic http authentication
 * <p>
 * Injects the UserDetailService to Spring to give user details necessary for
 * authentication.
 * 
 * @author Meltem
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.realm.name}")
	private String realmName;

	@Autowired
	private ClientCredentialsContainer clientCredentials;

	/**
	 * Implements a custom UserDetailsService, which gets the valid credentials
	 * from application context through {@link ClientCredentialsContainer} and
	 * stores them for in-memory authentication
	 * 
	 * @param auth
	 *            Spring's Authentication Manager object, it is injected by
	 *            Spring for us to configure.
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception { // NOSONAR
		for (ClientCredentials client : clientCredentials.getClients()) {
			auth.inMemoryAuthentication().withUser(client.getName()).password(client.getSecret()).authorities("CUST");
		}
	}

	/**
	 * Configure Spring Security for basic http authentication.
	 * 
	 * @param http
	 *            Spring's http security object, it is injected by Spring for us
	 *            to configure.
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception { // NOSONAR

		http.authorizeRequests().anyRequest().authenticated().antMatchers("/**/**").hasRole("CUST").and().httpBasic()
				.realmName(realmName).authenticationEntryPoint(new BasicAuthenticationEntryPoint() {
					@Override
					public void afterPropertiesSet() throws Exception {
						setRealmName(realmName);
						super.afterPropertiesSet();
					}
				}).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf()
				.disable();
	}

	/**
	 * Tells to Spring Security to ignore some of the request to allow
	 * Pre-flight [OPTIONS] request from browser.
	 * 
	 * @param web
	 *            Spring's web security object, it is injected by Spring for us
	 *            to configure.
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception { // NOSONAR
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

}
