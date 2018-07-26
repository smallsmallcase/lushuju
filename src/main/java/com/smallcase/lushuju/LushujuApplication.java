package com.smallcase.lushuju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class LushujuApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(LushujuApplication.class, args);
	}
}
