package net.yesiltas.sample.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
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
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/**/*.js", "/**/*.css",
					"/**/images/*.*");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception { // NOSONAR
			http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {		
				@Override
				public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception)
						throws IOException, ServletException {
					resp.sendError(401, exception.getMessage());
					
				}
			});
			http.authorizeRequests()
					.antMatchers("/index.html", "/**/default.html", "/**/main.html", "/**/login.html", "/**/logout")
					.permitAll().anyRequest().authenticated()
					.and().formLogin()
					.failureHandler(new CustomAuthenticationResultHandler())
					.successHandler(new CustomAuthenticationResultHandler())
					.loginProcessingUrl("/cust/login").permitAll()
					.and().logout()
					.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.ACCEPTED))
					.logoutUrl("/cust/logout").permitAll().and().csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		}

		private class CustomAuthenticationResultHandler
				implements AuthenticationFailureHandler, AuthenticationSuccessHandler {

			@Override
			public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
					AuthenticationException exception) throws IOException, ServletException {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
			}

			@Override
			public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
					throws IOException, ServletException {
				resp.setStatus(HttpServletResponse.SC_OK);
			}
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { // NOSONAR
		auth.userDetailsService(username -> {
			ClientAdapter adapter = adapterFactory.getAdapterForModule("cust");
			Map<String, String> uriVars = new HashMap<>();
			uriVars.put("username", username);
			ResponseEntity<User> entity = adapter.callService("/user/{username}", null, User.class, HttpMethod.GET,
					uriVars);
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