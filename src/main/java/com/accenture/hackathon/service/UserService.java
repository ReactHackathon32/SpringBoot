package com.accenture.hackathon.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import com.accenture.hackathon.dto.ChangePasswordRequest;
import com.accenture.hackathon.dto.GenericResponse;
import com.accenture.hackathon.dto.LoginResponse;
import com.accenture.hackathon.dto.RegistrationRequest;
import com.accenture.hackathon.dto.SavePasswordRequest;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.error.GenericDeviationException;

import jakarta.validation.Valid;

public interface UserService {

	User fetchUserById(UUID userId) throws NoSuchElementException;
	
	User fetchUserByEmail(String email) throws NoSuchElementException;

	User registerUser(RegistrationRequest userDetail) throws GenericDeviationException;

	LoginResponse verifyLogin(String email, String rawPassword);

	GenericResponse savePassword(SavePasswordRequest request) throws GenericDeviationException;

	boolean changePassword( ChangePasswordRequest request) throws NoSuchElementException, GenericDeviationException;
}
