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
//	@SequenceGenerator(
//			name = "parking_sequence",
//			sequenceName = "parking_sequence",
//			allocationSize = 10
//	)
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "parking_sequence"
//	)
	@GeneratedValue(strategy= GenerationType.UUID)
	@Column(name="parking_id")
	private UUID parkingId;
	
	@OneToOne
	@JoinColumn(
			name = "user_id",
			referencedColumnName = "user_id"
	)
	private User user;
	
	@ManyToOne()
	@JoinColumn(
			name="carpark_id",
			referencedColumnName = "carpark_id"
	)
	private Carpark carpark;
	
	@Column(name="start_time")
	private LocalDateTime startTime;
	
	@Column(name="priced_time")
	private LocalDateTime pricedTime;
	
	@Column(name="price")
	private float price;
}
