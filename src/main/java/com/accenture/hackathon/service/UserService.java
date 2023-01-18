package com.accenture.hackathon.service;

import java.util.UUID;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.model.UserRegistrationModel;

public interface UserService {

	User saveUser(User user);

	User fetchUserbyId(UUID userId);

	User registerUser(UserRegistrationModel userInfo);
	
	

}
