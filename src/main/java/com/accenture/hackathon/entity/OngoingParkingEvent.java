package com.accenture.hackathon.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name="tbl_ongoing_parking")
public class OngoingParkingEvent {
	
	@Id
	@GeneratedValue(strategy= GenerationType.UUID)
	@Column(name="parking_id")
	private UUID parkingId;
	
	@OneToOne
	@JoinColumn(
			name = "user_id",
			nullable = false,
			referencedColumnName = "user_id"
	)
	private User user;
	
	@ManyToOne()
	@JoinColumn(
			name="carpark_id",
			nullable = false,
			referencedColumnName = "carpark_id"
	)
	private Carpark carpark;
	
	@Column(name="start_time",
			nullable = false)
	private LocalDateTime startTime;
	
	@Column(name="priced_time",
			nullable = false)
	private LocalDateTime pricedTime;
	
	@Column(name="price",
			nullable = false)
	private float price;
}
