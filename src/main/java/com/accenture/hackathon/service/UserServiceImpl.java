package com.accenture.hackathon.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.dto.ChangePasswordRequest;
import com.accenture.hackathon.dto.GenericResponse;
import com.accenture.hackathon.dto.LoginResponse;
import com.accenture.hackathon.dto.RegistrationRequest;
import com.accenture.hackathon.dto.SavePasswordRequest;
import com.accenture.hackathon.entity.PasswordResetToken;
import com.accenture.hackathon.entity.SessionToken;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.error.GenericDeviationException;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.accenture.hackathon.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TokenService tokenService;

	@Override
	public User fetchUserById(UUID userId) throws NoSuchElementException {
		return userRepo.findById(userId).orElseThrow();
	}

	@Override
	public User fetchUserByEmail(String email) throws NoSuchElementException{
		return userRepo.findByEmail(email).orElseThrow();
	}	
	
	@Override
	public User registerUser(RegistrationRequest userDetail) throws GenericDeviationException {
		if(!userDetail.getPassword().equals(userDetail.getConfirmPassword())) {
			throw new GenericDeviationException("Password does not match");
		}
		 
		if(userRepo.findByEmail(userDetail.getEmail()).isPresent()) {
			throw new GenericDeviationException("Email already exists");
		}
		
		User user = userRepo.save(User.builder()
									.email(userDetail.getEmail())
									.firstName(userDetail.getFirstName())
									.lastName(userDetail.getLastName())
									.password(passwordEncoder.encode(userDetail.getPassword()))
									.enabled(false)
									.build());
		
		return user;
	}
	
	@Override
	public LoginResponse verifyLogin(String email, String rawPassword) throws NoSuchElementException {
		User user = userRepo.findByEmail(email).orElseThrow();
		
		if(!passwordEncoder.matches(rawPassword, user.getPassword())) {
			return LoginResponse.builder()
								.status("invalid username/password")
								.build();
		}
		if(!user.getEnabled()) {
			return LoginResponse.builder()
					.status("email not verified")
					.build();
		}
		
		SessionToken token = tokenService.saveSessionToken(
				SessionToken.builder()
							.token(UUID.randomUUID().toString())
							.user(user)
							.build());
		
		return LoginResponse.builder()
							.status("success")
							.sessionToken(token.getToken())
							.userId(user.getUserId())
							.build();
	}

	@Override
	public GenericResponse savePassword(SavePasswordRequest request) throws GenericDeviationException {
    	if(!request.getPassword().equals(request.getConfirmPassword())) {
    		throw new GenericDeviationException("Password does not match");
    	}
    	
    	PasswordResetToken resetToken = tokenService.fetchResetTokenByToken(request.getToken());
    	
    	if(resetToken.getExpireTime().isBefore(LocalDateTime.now())) {
	    	throw new GenericDeviationException("Token is expired");
	    }
    	
    	User user = resetToken.getUser();
    	user.setPassword(passwordEncoder.encode(request.getPassword()));
    	userRepo.save(user);
    	
		return new GenericResponse("success");
	}

	@Override
	public boolean changePassword(ChangePasswordRequest request) throws NoSuchElementException, GenericDeviationException {
		User user = userRepo.findByEmail(request.getEmail()).orElseThrow();
		
		if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			throw new GenericDeviationException("Old Password is invalid");
		}
		
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		userRepo.save(null);
		
		return true;
	}
}
