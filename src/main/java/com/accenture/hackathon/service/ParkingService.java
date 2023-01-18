package com.accenture.hackathon.service;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;

public interface ParkingService {

	OngoingParkingEvent startParkingEvent(User user, Carpark carpark);

}
