package com.accenture.hackathon.service;

import java.util.UUID;

import com.accenture.hackathon.entity.User;

public interface UserService {

	User saveUser(User user);

	User fetchUserbyId(UUID userId);

}
