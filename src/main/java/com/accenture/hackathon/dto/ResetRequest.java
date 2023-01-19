package com.accenture.hackathon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetRequest {
	@NotNull
	@NotEmpty
	@Email
	String email;
	
	@NotNull
	@NotEmpty
	String url;
}
