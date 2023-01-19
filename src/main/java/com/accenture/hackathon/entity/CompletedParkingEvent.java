package com.accenture.hackathon.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbl_completed_parking")
public class CompletedParkingEvent {
	
	@Id
	@Column(name="parking_id")
	private UUID parkingId;
	
	@ManyToOne()
	@JoinColumn(
			name="user_id",
			referencedColumnName = "user_id"
	)
	private User user;
	
	@ManyToOne()
	@JoinColumn(
			name="carpark_id",
			referencedColumnName = "carpark_id"
	)
	private Carpark carpark;
	
	@Column(name="start_time",
			nullable = false)
	private LocalDateTime startTime;
	
	@Column(name="end_time",
			nullable = false)
	private LocalDateTime endTime;
	
	@Column(name="price",
			nullable = false)
	private float price;
}
