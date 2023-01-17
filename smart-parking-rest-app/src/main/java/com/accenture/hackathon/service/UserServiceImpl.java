package com.accenture.hackathon.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User fetchUserbyId(UUID userId) {
		return userRepo.findById(userId).get();
	}
}
