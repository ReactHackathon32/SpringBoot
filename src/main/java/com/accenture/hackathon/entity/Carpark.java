package com.accenture.hackathon.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@GeneratedValue(strategy= GenerationType.UUID)
	@Column(name="carpark_id")
	private UUID carparkId;
	
	@Column(name="carpark_name")
	private String carparkName;
	
	@Column(name="base_price")
	private float basePrice;
	
	@Column(name="dynamic_price")
	private float dynamicPrice;
	
	@Column(name="total_lots")
	private int totalLots;
	
	@Column(name="available_lots")
	private int availableLots;
}
