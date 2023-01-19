package com.accenture.hackathon.dto;

import java.util.UUID;

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
public class StartParking {
	
	@NotNull
	@NotEmpty
	UUID carparkId;
	
	@NotNull
	@NotEmpty
	UUID userId;
}
