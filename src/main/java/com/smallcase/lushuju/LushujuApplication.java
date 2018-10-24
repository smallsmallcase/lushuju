package com.smallcase.lushuju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class LushujuApplication{

	public static void main(String[] args) {
		SpringApplication.run(LushujuApplication.class, args);
	}


}
