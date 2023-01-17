package com.accenture.hackathon.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.repository.CompletedParkingRepository;
import com.accenture.hackathon.repository.OngoingParkingRepository;

@Service
public class ParkingServiceImpl implements ParkingService{
	
	@Autowired
	private OngoingParkingRepository ongoingParkingRepo;
	
	@Autowired
	private CompletedParkingRepository completedParkingRepo;

	@Override
	public OngoingParkingEvent startParkingEvent(User user, Carpark carpark) {
		OngoingParkingEvent newParking = OngoingParkingEvent.builder()
				.user(user)
				.carpark(carpark)
				.startTime(LocalDateTime.now())
				.pricedTime(LocalDateTime.now())
				.price(0.00f)
				.build();
		
		return ongoingParkingRepo.save(newParking);
	}
	
	
}
