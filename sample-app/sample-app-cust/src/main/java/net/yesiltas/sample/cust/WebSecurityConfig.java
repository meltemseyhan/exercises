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
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Value("${security.realm.name}")
    private String realmName;
	
    @Autowired
    private ClientCredentialsContainer clientCredentials;	
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception { //NOSONAR
    	for (ClientCredentials client : clientCredentials.getClients()) {
    		auth.inMemoryAuthentication().withUser(client.getName()).password(client.getSecret()).authorities("CUST");
		}
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception { //NOSONAR
  
      http.authorizeRequests()
        .anyRequest().authenticated()
        .antMatchers("/**/**").hasRole("CUST")
        .and().httpBasic().realmName(realmName).authenticationEntryPoint(new BasicAuthenticationEntryPoint() {
            @Override
            public void afterPropertiesSet() throws Exception {
                setRealmName(realmName);
                super.afterPropertiesSet();
            }
        })
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().csrf().disable();
    }
     

    @Override
    public void configure(WebSecurity web) throws Exception { //NOSONAR
        //To allow Pre-flight [OPTIONS] request from browser
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
    
}
