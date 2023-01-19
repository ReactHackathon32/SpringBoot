package com.accenture.hackathon.service;

import java.util.List;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.entity.CompletedParkingEvent;
import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.error.GenericDeviationException;

public interface ParkingService {	
	OngoingParkingEvent startParkingEvent(User user, Carpark carpark) throws GenericDeviationException;

	OngoingParkingEvent fetchOngoingParkingEventByUser(User user);

	CompletedParkingEvent stopParkingEvent(User user);

	List<CompletedParkingEvent> getAllParkingEvent(User user);
	
	OngoingParkingEvent updateParkingFee(OngoingParkingEvent ongoingParking);

}
