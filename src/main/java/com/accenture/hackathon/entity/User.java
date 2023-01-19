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
@Table(name="tbl_user")
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
	@Column(name="password",
			length=60,
			nullable = false)
	private String password;
	
	@Column(name="first_name",
			nullable = false)
	private String firstName;
	
	@Column(name="last_name",
			nullable = false)
	private String lastName;
	
	@Column(name="enabled",
			nullable = false)
	private Boolean enabled;
}
