package com.accenture.hackathon.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.entity.CompletedParkingEvent;
import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.error.GenericDeviationException;
import com.accenture.hackathon.repository.CompletedParkingRepository;
import com.accenture.hackathon.repository.OngoingParkingRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingServiceImpl implements ParkingService{
	
	@Autowired
	private OngoingParkingRepository ongoingParkingRepo;
	
	@Autowired
	private CompletedParkingRepository completedParkingRepo;
	
	@Autowired
	private DynamicPriceService dynamicPriceService;
	
	@Override
	public OngoingParkingEvent startParkingEvent(User user, Carpark carpark) throws GenericDeviationException {
		if(ongoingParkingRepo.findByUser(user).isPresent()) {
			throw new GenericDeviationException("user is already parking");
		}
		OngoingParkingEvent newParking = OngoingParkingEvent.builder()
				.user(user)
				.carpark(carpark)
				.startTime(LocalDateTime.now())
				.pricedTime(LocalDateTime.now())
				.price(0.00f)
				.build();
		
		return ongoingParkingRepo.save(newParking);
	}

	@Override
	public OngoingParkingEvent fetchOngoingParkingEventByUser(User user) throws NoSuchElementException {
		OngoingParkingEvent currentParking = ongoingParkingRepo.findByUser(user).orElseThrow();
		LocalDateTime nowTime = LocalDateTime.now();
		float updatedPrice = currentParking.getPrice();
		
		currentParking.setPrice(updatedPrice);
		currentParking.setPricedTime(nowTime);
		
		return ongoingParkingRepo.save(currentParking);
	}
	
	@Transactional
	@Override
	public CompletedParkingEvent stopParkingEvent(User user) throws NoSuchElementException {
		OngoingParkingEvent currentParking = ongoingParkingRepo.findByUser(user).orElseThrow();
		LocalDateTime nowTime = LocalDateTime.now();
		float finalPrice = currentParking.getPrice();
		finalPrice += dynamicPriceService.calculateCost(currentParking.getPricedTime(),
														nowTime,
														currentParking.getCarpark().getDynamicPrice());
		finalPrice = dynamicPriceService.roundPrice(finalPrice);
		
		CompletedParkingEvent completedParking = CompletedParkingEvent.builder()
																		.carpark(currentParking.getCarpark())
																		.parkingId(currentParking.getParkingId())
																		.user(currentParking.getUser())
																		.startTime(currentParking.getStartTime())
																		.endTime(nowTime)
																		.price(finalPrice)
																		.build();
		
		ongoingParkingRepo.deleteById(currentParking.getParkingId());
		
		return completedParkingRepo.save(completedParking);
	}

	@Override
	public List<CompletedParkingEvent> getAllParkingEvent(User user) {
		return completedParkingRepo.findAllByUser(user);
	}
	
	@Override
	public OngoingParkingEvent updateParkingFee(OngoingParkingEvent ongoingParking) {
		LocalDateTime nowTime = LocalDateTime.now();
		float dynamicPrice = ongoingParking.getCarpark().getDynamicPrice();
		float parkingAddedCost = dynamicPriceService.calculateCost(ongoingParking.getPricedTime(), nowTime, dynamicPrice);
		ongoingParking.setPricedTime(nowTime);
		ongoingParking.setPrice(ongoingParking.getPrice() + parkingAddedCost);
		
		return ongoingParking;
	}
	
}
