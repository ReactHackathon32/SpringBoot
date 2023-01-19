package com.accenture.hackathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.repository.OngoingParkingRepository;

@Service
public class SchedulerService {
	
	@Autowired
	DynamicPriceService dynamicPriceService;
	
	@Autowired
	OngoingParkingRepository ongoingParkingRepo;
	
	@Autowired
	ParkingService parkingService;
	
	@Scheduled(cron = "0 0/5 * * * *")
	public void scheduledUpdate() {
		List<OngoingParkingEvent> ongoingParkingList = ongoingParkingRepo.findAll();
		for(OngoingParkingEvent ongoingParking : ongoingParkingList) {	
			ongoingParkingRepo.save(parkingService.updateParkingFee(ongoingParking));
		}
		
		dynamicPriceService.updateDynamicPrice();
	}
}
