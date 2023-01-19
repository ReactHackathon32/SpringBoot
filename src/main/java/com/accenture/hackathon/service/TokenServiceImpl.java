package com.accenture.hackathon.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.accenture.hackathon.entity.PasswordResetToken;
import com.accenture.hackathon.entity.SessionToken;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.entity.VerificationToken;
import com.accenture.hackathon.error.GenericDeviationException;
import com.accenture.hackathon.repository.PasswordResetTokenRepository;
import com.accenture.hackathon.repository.SessionTokenRepository;
import com.accenture.hackathon.repository.UserRepository;
import com.accenture.hackathon.repository.VerificationTokenRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {
	
	final static int VERIFICATION_EXPIRATION_MINUTES = 15;
	final static int SESSION_EXPIRATION_HOURS = 24;
	final static int RESET_EXPIRATION_MINUTES = 15;
	
	@Autowired
	VerificationTokenRepository verifyTokenRepo;
	
	@Autowired
	SessionTokenRepository sessionTokenRepo;
	
	@Autowired
	PasswordResetTokenRepository resetTokenRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private EmailService emailService;

	@Override
	public VerificationToken fetchVerificationTokenByUser(User user) throws NoSuchElementException {
		return verifyTokenRepo.findByUser(user).orElseThrow();
	}
	
	@Override
	public String generateVerificationLink(HttpServletRequest request, String token) {
		return "http://" +
				request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath() +
                "/verifyRegistration?token=" +
                token;
	}

	@Override
	public Boolean validateVerificationToken(String token) throws NoSuchElementException, GenericDeviationException {
		VerificationToken verifyToken = verifyTokenRepo.findByToken(token).orElseThrow();
		
		User user = verifyToken.getUser();
		
	    if(verifyToken.getExpireTime().isBefore(LocalDateTime.now())) {
	    	throw new GenericDeviationException("Token is expired");
	    }
		
	    user.setEnabled(true);
	    userRepo.save(user);
	    return true;
	}
	
	@Override
	public SessionToken saveSessionToken(SessionToken token) {
		token.setExpireTime(LocalDateTime.now().plusHours(SESSION_EXPIRATION_HOURS));
		return sessionTokenRepo.save(token);
	}

	@Override
	public void deleteOldVerificationToken(User user) {
		verifyTokenRepo.deleteByUser(user);
	}
	
	@Async
	@Override
	public void sendVerificationToken(User user, HttpServletRequest request) {
		VerificationToken token = verifyTokenRepo.save(
				VerificationToken.builder()
								.token(UUID.randomUUID().toString())
								.user(user)
								.expireTime(LocalDateTime.now().plusMinutes(VERIFICATION_EXPIRATION_MINUTES))
								.build());
		
		String link = generateVerificationLink(request, token.getToken());
		emailService.send(
				user.getEmail(),
				emailService.buildConfirmationEmail(user.getFirstName() + " " + user.getLastName(),
				link),
				"Email Verification");
	}
	
	@Async
	@Override
	public void sendResetToken(User user, String url) {
		PasswordResetToken token = resetTokenRepo.save(
				PasswordResetToken.builder()
				.token(UUID.randomUUID().toString())
				.user(user)
				.expireTime(LocalDateTime.now().plusMinutes(VERIFICATION_EXPIRATION_MINUTES))
				.build());
		
		String link = url +
                "?token=" +
                token.getToken();
		
		emailService.send(
				user.getEmail(),
				emailService.buildConfirmationEmail(user.getFirstName() + " " + user.getLastName(),
				link),
				"Email Verification");
	}

	@Override
	public PasswordResetToken fetchResetTokenByToken(String token) throws NoSuchElementException {
		return resetTokenRepo.findByToken(token).orElseThrow();
	}

	@Override
	public void validateSession(String token, UUID id) throws GenericDeviationException {
		try {
			SessionToken sessionToken = sessionTokenRepo.getByToken(token).orElseThrow();
			
			if(sessionToken.getUser().getUserId() != id) {
				throw new GenericDeviationException();
			}
			
			if(sessionToken.getExpireTime().isBefore(LocalDateTime.now())) {
				sessionTokenRepo.deleteById(sessionToken.getId());
				throw new GenericDeviationException();
			}
		} catch(Exception e) {
			throw new GenericDeviationException("unauthenticated");
		}
	}

	@Override
	public void validateSession(String token, String email) throws GenericDeviationException {
		try {
			SessionToken sessionToken = sessionTokenRepo.getByToken(token).orElseThrow();
			
			if(!sessionToken.getUser().getEmail().equals(email)) {
				throw new GenericDeviationException();
			}
			
			if(sessionToken.getExpireTime().isBefore(LocalDateTime.now())) {
				sessionTokenRepo.deleteById(sessionToken.getId());
				throw new GenericDeviationException();
			}
		} catch(Exception e) {
			throw new GenericDeviationException("unauthenticated");
		}
	}
}
