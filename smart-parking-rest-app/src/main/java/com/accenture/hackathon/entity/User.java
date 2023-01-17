package com.accenture.hackathon.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
		name="tbl_user",
		uniqueConstraints = @UniqueConstraint(
				name = "email_unique",
				columnNames = "email"
		)
)
public class User {
	
	@Id
//	@SequenceGenerator(
//			name = "user_sequence",
//			sequenceName = "user_sequence",
//			allocationSize = 10
//	)
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "user_sequence"
//	)
	@GeneratedValue(strategy= GenerationType.UUID)
	@Column(name="user_id")
	private UUID userId;
	
	@Column(
			name="email",
			nullable = false
	)
	
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@OneToOne(
			mappedBy = "user"
	)
	private OngoingParkingEvent ongoingParking;
}
