package com.accenture.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		System.out.println("saveUser entered");
		return userService.saveUser(user);
	}
	
	@GetMapping("/")
	public String helloWorld() {
		return "hello World";
	}
}
