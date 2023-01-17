package com.accenture.hackathon.service;

import java.util.UUID;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.entity.User;

public interface CarparkService {

	Carpark fetchCarparkbyId(UUID carparkId);

	Carpark saveCarpark(Carpark carpark);

}
