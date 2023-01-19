package com.accenture.hackathon;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.accenture.hackathon.service.DynamicPriceService;

@SpringBootApplication
public class SmartParkingRestAppApplication {
  
	public static void main(String[] args) {
		
		
		SpringApplication.run(SmartParkingRestAppApplication.class, args);
	}

}
