package net.yesiltas.sample.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import net.yesiltas.sample.client.adapter.ClientAdapter;
import net.yesiltas.sample.client.adapter.ClientAdapterFactory;
import net.yesiltas.sample.cust.model.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	ClientAdapterFactory adapterFactory;

	@Configuration
	@Order(1)
	public static class CustomerConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(WebSecurity web) throws Exception { // NOSONAR
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/**/*.js", "/**/*.css", "/**/images/*.*");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception { //NOSONAR
			http.authorizeRequests()
			.antMatchers("/index.html", "/**/default.html", "/**/main.html", "/**/login.html").permitAll()		
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/cust/login").permitAll()
			.and().logout().logoutUrl("/cust/logout").permitAll()
			.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { //NOSONAR
		auth.userDetailsService(username -> {
			ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
			Map<String, String> uriVars = new HashMap<>();
			uriVars.put("username", username);
			ResponseEntity<User> entity = adapter.callService("/user/{username}", null, User.class, HttpMethod.GET, uriVars);
			User user = entity.getBody();
			if (user != null) {
				// todo: Authority list must be implemented //NOSONAR
				return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
						user.getEnabled(), !user.getExpired(), true, !user.getLocked(),
						AuthorityUtils.createAuthorityList("CUST"));
			} else {
				throw new UsernameNotFoundException("could not find the user '" + username + "'");
			}
		});
	}
}