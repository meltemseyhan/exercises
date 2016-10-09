package net.yesiltas.sample.common;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * This class tells the spring boot to start a stand-alone application.
 * 
 * Normally "common" module is not expected to run as a separate application itself.
 * It is intended to be used as a JAR library of common functionality that is used in
 * multiple back-end modules.
 * 
 * This initializer is kept for development and test purposes
 * 
 * @author Meltem
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
public class CommonApplicationInitializer { //NOSONAR
	
    /** 
     * By providing this method, it is possible to package the project as a jar,
     * and running it as a stand-alone java application. Spring boot will trigger an
     * embedded Tomcat server and serve the application.
     * 
     * @param args command line arguments to start application
     */	
	public static void main(String[] args) {
		SpringApplication.run(CommonApplicationInitializer.class, args); //NOSONAR
	}
	 
}
