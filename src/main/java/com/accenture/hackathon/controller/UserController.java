package com.accenture.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.model.UserLoginDTO;
import com.accenture.hackathon.model.UserRegistrationDTO;
import com.accenture.hackathon.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user/register")
	public User registerUser(@RequestBody UserRegistrationDTO userDetail) {
		System.out.println("entered method in controller");
		return userService.registerUser(userDetail);
	}
	
	@PostMapping("/user/login")
	public String loginUser(@RequestBody @Valid UserLoginDTO loginDetails) {
		if(userService.verifyLogin(loginDetails.getEmail(), loginDetails.getPassword())) {
			return "success";
		}
		return "failure";
	}
	
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "hello world";
	}
}
