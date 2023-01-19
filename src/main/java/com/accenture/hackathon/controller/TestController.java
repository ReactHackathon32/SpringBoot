package com.accenture.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.hackathon.service.DynamicPriceService;


@RestController
@CrossOrigin(origins="*")
public class TestController {

@Autowired
DynamicPriceService dps;
	
@GetMapping("/test")
public String test(){
	dps.generateCarParks();
//	dps.updateDynamicPrice();
	return "test";
}
	


}
