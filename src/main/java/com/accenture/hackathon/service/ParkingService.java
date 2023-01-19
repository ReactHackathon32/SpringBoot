package com.accenture.hackathon.service;

import java.util.List;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.entity.CompletedParkingEvent;
import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;

public interface ParkingService {	
	OngoingParkingEvent startParkingEvent(User user, Carpark carpark);

	OngoingParkingEvent fetchOngoingParkingEventByUser(User user);

	CompletedParkingEvent stopParkingEvent(User user);

	List<CompletedParkingEvent> getAllParkingEvent(User user);

}
