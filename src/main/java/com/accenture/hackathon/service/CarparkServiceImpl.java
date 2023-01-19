package com.accenture.hackathon.service;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.Carpark;
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

	@Override
	public Carpark fetchCarparkbyName(String name) {
		return carparkRepo.findByCarparkName(name);
	}

	@Override
	public List<Carpark> fetchAllCarpark() {
		return carparkRepo.findAll();
	}

}
