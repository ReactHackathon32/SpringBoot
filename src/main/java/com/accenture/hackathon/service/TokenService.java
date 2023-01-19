package com.accenture.hackathon.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import com.accenture.hackathon.entity.PasswordResetToken;
import com.accenture.hackathon.entity.SessionToken;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.entity.VerificationToken;
import com.accenture.hackathon.error.GenericDeviationException;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenService {
	
	VerificationToken fetchVerificationTokenByUser(User user) throws NoSuchElementException;
	
	void sendVerificationToken(User user, HttpServletRequest request);
	
	void sendResetToken(User user, String url);
	
	String generateVerificationLink(HttpServletRequest request, String token);

	SessionToken saveSessionToken(SessionToken token);
	
	Boolean validateVerificationToken(String token) throws NoSuchElementException, GenericDeviationException;

	void deleteOldVerificationToken(User user);

	PasswordResetToken fetchResetTokenByToken(String token) throws NoSuchElementException;

	void validateSession(String token, UUID id) throws GenericDeviationException;
	
	void validateSession(String token, String email) throws GenericDeviationException;
}
