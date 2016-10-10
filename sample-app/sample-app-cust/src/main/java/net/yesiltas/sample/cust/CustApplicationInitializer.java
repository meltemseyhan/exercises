package net.yesiltas.sample.cust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This class tells the spring boot to start a web application.
 * 
 * @author Meltem
 */

@SpringBootApplication
public class CustApplicationInitializer extends SpringBootServletInitializer {

	/**
	 * By providing this method, it is possible to package the project as a jar,
	 * and running it as a stand-alone java application. Spring boot will
	 * trigger an embedded Tomcat server and serve the application.
	 * 
	 * @param args
	 *            command line arguments to start application
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustApplicationInitializer.class, args); // NOSONAR
	}

	/**
	 * This method is used by Spring Boot to initialize the web application.
	 * When the project is packaged as WAR and deployed to an application server
	 * this method is used.
	 * 
	 * Tells Spring to scan all the classes in this package and all the
	 * sub-packages to load any configuration.
	 * 
	 * @param application
	 *            Spring's builder for the application context, it is injected
	 *            by Spring for us to configure.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CustApplicationInitializer.class);
	}
}
