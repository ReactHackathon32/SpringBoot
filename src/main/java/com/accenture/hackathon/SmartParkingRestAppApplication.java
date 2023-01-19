package com.accenture.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SmartParkingRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartParkingRestAppApplication.class, args);
	}

}
