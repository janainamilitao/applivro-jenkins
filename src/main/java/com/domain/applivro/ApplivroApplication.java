package com.domain.applivro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplivroApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplivroApplication.class, args);
    }
    
    /*@Bean
    public CommandLineRunner test(ProfileService service) {
    	return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Profile pro = new Profile();
				pro.setName("Profile");
				pro.setEmail("jana@email.com");
				pro.setIpAddress("192.168.0.1");
				pro.setDateOfBirth(new Date());
				pro.setPostalCode("58000000");
				service.save(pro);
			}
		};
    }*/
    
}
