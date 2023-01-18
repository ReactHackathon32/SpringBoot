package com.accenture.hackathon.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@GeneratedValue(strategy= GenerationType.UUID)
	@Column(name="user_id")
	private UUID userId;
	
	@Column(
			name="email",
			nullable = false
	)
	
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name="password", length=60)
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@OneToOne(
			mappedBy = "user"
	)
	private OngoingParkingEvent ongoingParking;
}
