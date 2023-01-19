package com.accenture.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.service.CarparkService;

@RestController
public class CarparkController {
	
	@Autowired
	CarparkService carparkService;
	
	@PostMapping("/carpark")
	public Carpark saveCarpark(@RequestBody Carpark carpark) {
		System.out.println("saveUser entered");
		return carparkService.saveCarpark(carpark);
	}
	
	@GetMapping("/carpark")
	public List<Carpark> getAllCarpark() {
		return carparkService.fetchAllCarpark();
	}
	
	
}
