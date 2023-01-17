package com.accenture.hackathon.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.service.CarparkService;
import com.accenture.hackathon.service.ParkingService;
import com.accenture.hackathon.service.UserService;

@RestController
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private CarparkService carparkService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/parking/start/{carparkId}")
	public OngoingParkingEvent startParking(@RequestBody User user, @PathVariable("carparkId") UUID carparkId) {
		return parkingService.startParkingEvent(userService.fetchUserbyId(user.getUserId()), carparkService.fetchCarparkbyId(carparkId));
		
	}
}
