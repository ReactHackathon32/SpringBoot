package com.accenture.hackathon.controller;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.hackathon.dto.ChangePasswordRequest;
import com.accenture.hackathon.dto.GenericResponse;
import com.accenture.hackathon.dto.LoginRequest;
import com.accenture.hackathon.dto.LoginResponse;
import com.accenture.hackathon.dto.RegistrationRequest;
import com.accenture.hackathon.dto.ResetRequest;
import com.accenture.hackathon.dto.SavePasswordRequest;
import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.error.GenericDeviationException;
import com.accenture.hackathon.service.TokenService;
import com.accenture.hackathon.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private TokenService tokenService;
		
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable("id") UUID id, @RequestParam("token") String sessionToken) throws GenericDeviationException {
		tokenService.validateSession(sessionToken, id);
		return userService.fetchUserById(id);
	}
	
	@GetMapping("/user/email/{email}")
	public User getUserByEmail(@PathVariable("email") String email, @RequestParam("token") String sessionToken) throws GenericDeviationException {
		tokenService.validateSession(sessionToken, email);
		return userService.fetchUserByEmail(email);
	}
	
	@PostMapping("/user/register")
	public GenericResponse registerUser(@Valid @RequestBody RegistrationRequest userDetail, final HttpServletRequest request) throws GenericDeviationException {
		User user = userService.registerUser(userDetail);
		if(user == null) {
			throw new GenericDeviationException("Something went wrong...");
		}
		
		tokenService.sendVerificationToken(user, request);
		return new GenericResponse("success");
	}
	
    @GetMapping("/verifyRegistration")
    public GenericResponse verifyRegistration(@RequestParam("token") String token) {
        try {
			if(tokenService.validateVerificationToken(token)) {
				return new GenericResponse("success");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (GenericDeviationException e) {
			e.printStackTrace();
		}
        return new GenericResponse("failed");
    }
    
    @GetMapping("/resendVerifyToken")
    public GenericResponse resendVerificationToken(@RequestParam("email") String email,
                                          HttpServletRequest request) throws GenericDeviationException {
    	User user = userService.fetchUserByEmail(email);
    	
    	if(user.getEnabled()) {
    		throw new GenericDeviationException("user already enabled");
    	}
    	
    	tokenService.deleteOldVerificationToken(user);
    	tokenService.sendVerificationToken(user, request);
    	
    	return new GenericResponse("success");
    }
    
    @GetMapping("/resetPassword")
    public GenericResponse resetPassword(@RequestBody @Valid ResetRequest resetDetails) {
    	User user = userService.fetchUserByEmail(resetDetails.getEmail());
    	tokenService.sendResetToken(user, resetDetails.getUrl());
    	
    	return new GenericResponse("Password Reset Link Sent");
    }
    
    @PostMapping("/savePassword")
    public GenericResponse savePassword(@Valid @RequestBody SavePasswordRequest request) throws GenericDeviationException {  	
    	return userService.savePassword(request);
    }
    
    @PostMapping("/changePassword")
    public GenericResponse changePassword(@Valid @RequestBody ChangePasswordRequest request, @RequestParam("token") String sessionToken) throws NoSuchElementException, GenericDeviationException {
    	tokenService.validateSession(sessionToken, request.getEmail());
    	if(!userService.changePassword(request)) {
    		return new GenericResponse("failed"); 
    	}
    	return new GenericResponse("success");    	
    }
	
	@PostMapping("/user/login")
	public LoginResponse loginUser(@Valid @RequestBody LoginRequest loginDetails) throws NoSuchElementException {
		return userService.verifyLogin(loginDetails.getEmail(), loginDetails.getPassword());
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "hello world";
	}
}
