package com.accenture.hackathon.service;

import java.util.UUID;


import com.accenture.hackathon.entity.Carpark;

public interface CarparkService {

	Carpark fetchCarparkbyId(UUID carparkId);

	Carpark saveCarpark(Carpark carpark);
	
	Carpark fetchCarparkbyName(String name);

}
