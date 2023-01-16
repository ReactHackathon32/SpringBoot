package com.accenture.hackathon.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
	@SequenceGenerator(
			name = "parking_sequence",
			sequenceName = "parking_sequence",
			allocationSize = 10
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "parking_sequence"
	)
	@Column(name="parking_id")
	private Long parkingId;
	
	@OneToOne
	@JoinColumn(
			name = "user_id",
			referencedColumnName = "user_id"
	)
	private User user;
	
	@OneToOne
	@JoinColumn(
			name = "carpark_id",
			referencedColumnName = "carpark_id"
	)
	private Carpark carpark;
	
	@Column(name="start_time")
	private Date startTime;
}
