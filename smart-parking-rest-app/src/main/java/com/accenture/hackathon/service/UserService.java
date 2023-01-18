package com.accenture.hackathon.service;

import java.util.UUID;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.model.UserRegistrationDTO;

public interface UserService {

	User fetchUserbyId(UUID userId);

	User registerUser(UserRegistrationDTO userDetail);

	boolean verifyLogin(String email, String rawPassword);
}
