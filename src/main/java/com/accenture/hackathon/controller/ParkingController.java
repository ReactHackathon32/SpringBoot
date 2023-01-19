package com.accenture.hackathon.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.hackathon.dto.StartParking;
import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.entity.CompletedParkingEvent;
import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.error.GenericDeviationException;
import com.accenture.hackathon.service.CarparkService;
import com.accenture.hackathon.service.DynamicPriceService;
import com.accenture.hackathon.service.ParkingService;
import com.accenture.hackathon.service.TokenService;
import com.accenture.hackathon.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="*")
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private CarparkService carparkService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DynamicPriceService dynamicPriceService;
	
	@PostMapping("/parking/start")
	public OngoingParkingEvent startParking(@Valid @RequestBody StartParking parkingEvent, 
										@RequestParam("token") String sessionToken) throws GenericDeviationException, NoSuchElementException {
		tokenService.validateSession(sessionToken, UUID.fromString(parkingEvent.getUserId()));
		return parkingService.startParkingEvent(userService.fetchUserById(UUID.fromString(parkingEvent.getUserId())), carparkService.fetchCarparkbyId(UUID.fromString(parkingEvent.getCarparkId())));
	}
	
	@PostMapping("/parking/end/{id}")
	public CompletedParkingEvent stopParking(@PathVariable("id") String userId, @RequestParam("token") String sessionToken) throws GenericDeviationException, NoSuchElementException {
		tokenService.validateSession(sessionToken, UUID.fromString(userId));
		User user = userService.fetchUserById(UUID.fromString(userId));
		
		return parkingService.stopParkingEvent(user);		
	}
	
	@GetMapping("/parking/current/{id}")
	public OngoingParkingEvent currentParking(@PathVariable("id") String userId, @RequestParam("token") String sessionToken) throws GenericDeviationException, NoSuchElementException {
		tokenService.validateSession(sessionToken, UUID.fromString(userId));
		User user = userService.fetchUserById(UUID.fromString(userId));
		
		OngoingParkingEvent ongoingParkingEvent = parkingService.fetchOngoingParkingEventByUser(user);
		ongoingParkingEvent = parkingService.updateParkingFee(ongoingParkingEvent);

		float roundedPrice = dynamicPriceService.roundPrice(ongoingParkingEvent.getPrice());
		ongoingParkingEvent.setPrice(roundedPrice);
		
		return ongoingParkingEvent;
	}
	
	@GetMapping("/parking/complete/{id}")
	public List<CompletedParkingEvent> getAllParking(@PathVariable("id") String userId, @RequestParam("token") String sessionToken) throws GenericDeviationException {
		tokenService.validateSession(sessionToken, UUID.fromString(userId));
		User user = userService.fetchUserById(UUID.fromString(userId));
		
		return parkingService.getAllParkingEvent(user);
	}
	
	@GetMapping("/carpark")
	public List<Carpark> getAllCarpark() {
		return carparkService.fetchAllCarpark();
	}
}