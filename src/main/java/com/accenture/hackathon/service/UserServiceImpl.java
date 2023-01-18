package com.accenture.hackathon.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.model.UserRegistrationDTO;
import com.accenture.hackathon.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User fetchUserbyId(UUID userId) {
		return userRepo.findById(userId).get();
	}

	@Override
	public User registerUser(UserRegistrationDTO userDetail) {
		System.out.println("entered register method in service");
		User user = User.builder()
				.email(userDetail.getEmail())
				.firstName(userDetail.getFirstName())
				.lastName(userDetail.getLastName())
				.password(passwordEncoder.encode(userDetail.getPassword()))
				.enabled(false)
				.build();
		
		return userRepo.save(user);
	}

	@Override
	public boolean verifyLogin(String email, String rawPassword) {
		User user = userRepo.findByEmail(email);
		return passwordEncoder.matches(rawPassword, user.getPassword());
	}


}
