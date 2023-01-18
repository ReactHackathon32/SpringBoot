package com.accenture.hackathon.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.repository.CarparkRepository;

@Service
public class CarparkServiceImpl implements CarparkService{
	
	@Autowired
	private CarparkRepository carparkRepo;
	
	@Override
	public Carpark fetchCarparkbyId(UUID carparkId) {
		return carparkRepo.findById(carparkId).get();
	}

	@Override
	public Carpark saveCarpark(Carpark carpark) {
		return carparkRepo.save(carpark);
	}

}
