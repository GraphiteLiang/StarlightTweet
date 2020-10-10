package com.starlight.data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication

public class StarlightTweetApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(StarlightTweetApplication.class, args);
	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

}
