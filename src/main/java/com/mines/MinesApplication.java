package com.mines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@PropertySource(value = "file:application.properties")
@SpringBootApplication
//@EnableScheduling
public class MinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesApplication.class, args);
	}

}
