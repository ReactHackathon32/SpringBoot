package com.accenture.hackathon.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carpark {
	
	@Id
	@Column(name="carpark_id")
	private Long carparkId;
	
	@Column(name="base_price")
	private float basePrice;
	
	@OneToOne(
			mappedBy = "carpark"
	)
	private OngoingParkingEvent ongoingParking;	
}
