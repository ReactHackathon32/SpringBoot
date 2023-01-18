package com.accenture.hackathon.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.model.UserRegistrationModel;
=======
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.model.UserRegistrationDTO;
>>>>>>> 99b65180517c4b1d1044fd66bf2bd26161f51b55
import com.accenture.hackathon.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
<<<<<<< HEAD
	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}
=======
//	@Autowired
//	private PasswordEncoder passwordEncoder;
>>>>>>> 99b65180517c4b1d1044fd66bf2bd26161f51b55

	@Override
	public User fetchUserbyId(UUID userId) {
		return userRepo.findById(userId).get();
	}

	@Override
<<<<<<< HEAD
	public User registerUser(UserRegistrationModel userInfo) {
		// TODO Auto-generated method stub
		return null;
	}
=======
	public User registerUser(UserRegistrationDTO userDetail) {
		System.out.println("entered register method in service");
		User user = User.builder()
				.email(userDetail.getEmail())
				.firstName(userDetail.getFirstName())
				.lastName(userDetail.getLastName())
//				.password(passwordEncoder.encode(userDetail.getPassword()))
				.password(userDetail.getPassword())
				.enabled(false)
				.build();
		
		return userRepo.save(user);
	}

	@Override
	public boolean verifyLogin(String email, String rawPassword) {
		User user = userRepo.findByEmail(email);
//		return passwordEncoder.matches(rawPassword, user.getPassword());
		return rawPassword.equals(user.getPassword());
	}


>>>>>>> 99b65180517c4b1d1044fd66bf2bd26161f51b55
}
