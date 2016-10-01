package net.yesiltas.sample.cust;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication

public class CustApplicationInitializer extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(CustApplicationInitializer.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CustApplicationInitializer.class);
    }	
}
