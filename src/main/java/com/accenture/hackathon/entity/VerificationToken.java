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
@Table(name="tbl_verify_email_token")
public class VerificationToken {
	
	@Id
	@GeneratedValue(strategy= GenerationType.UUID)
	@Column(name="verification_token_id")
	private UUID id;
	
	@Column(name="verify_token",
			nullable = false)
	private String token;
	
	@Column(name="expire_time",
			nullable = false)
	private LocalDateTime expireTime;
	
	@ManyToOne
	@JoinColumn(
			name="user_id",
			nullable = false,
			referencedColumnName = "user_id"
	)
	private User user;
}
