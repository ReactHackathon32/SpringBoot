package com.accenture.hackathon.model;

import java.util.UUID;


import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationModel {
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
}
