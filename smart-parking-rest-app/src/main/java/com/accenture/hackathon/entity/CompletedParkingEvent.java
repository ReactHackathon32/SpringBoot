package com.accenture.hackathon.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	private Long parkingId;
	
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
	
	@Column(name="start_time")
	private Date startTime;
	
	@Column(name="end_time")
	private Date endTime;
}
