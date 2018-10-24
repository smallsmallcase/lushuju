package com.smallcase.lushuju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class LushujuApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(LushujuApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LushujuApplication.class);
    }


    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/*");
        bean.setFilter(new CrosFilter());
        return bean;
    }
}
