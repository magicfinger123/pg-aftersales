package com.pgagtersales.pgaftersales;

import com.pgagtersales.pgaftersales.app.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PgAftersalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgAftersalesApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}
	@Bean(name = "ApplicationProperties")
	public ApplicationProperties getApplicationProperties(){
		return new ApplicationProperties();
	}

}
