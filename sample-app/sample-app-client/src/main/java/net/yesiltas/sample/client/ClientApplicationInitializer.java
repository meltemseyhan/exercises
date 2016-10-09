package net.yesiltas.sample.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * This class tells the spring boot to start a web application.
 * Since client project uses model classes from the other server modules,
 * and since those classes are annotated with JPA annotations,
 * client project depends JPA related libraries to compile.
 * <p>
 * When Spring finds these JPA libraries on classpath, it will try to load JPA,
 * which will require database driver and connection to exist.
 * <p>
 * But Client project should not depend on any database,
 * it is merely for serving the UI.
 * <p>
 * To prevent Spring from loading JPA, related configuration classes are excluded.
 * This way project compiles, model classes are reused, 
 * and client project is free from database dependency.
 * 
 * @author Meltem
 */

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })

public class ClientApplicationInitializer extends SpringBootServletInitializer{
	
    /** 
     * By providing this method, it is possible to package the project as a jar,
     * and running it as a stand-alone java application. Spring boot will trigger an
     * embedded Tomcat server and serve the application.
     * 
     * @param args command line arguments to start application
     */	
	public static void main(String[] args) {
		SpringApplication.run(ClientApplicationInitializer.class, args); //NOSONAR
	}
	
    /** 
     * This method is used by Spring Boot to initialize the web application.
     * When the project is packaged as WAR and deployed to an application server
     * this method is used.
     * 
     * Tells Spring to scan all the classes in this package and all the sub-packages
     * to load any configuration.
     * 
     * @param application 	Spring's builder for the application context,
     * 						it is injected by Spring for us to configure.
     */		
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ClientApplicationInitializer.class);
    }	
	 
}
