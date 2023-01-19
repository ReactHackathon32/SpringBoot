package com.accenture.hackathon.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name="tbl_session_token")
public class SessionToken {
	@Id
	@GeneratedValue(strategy= GenerationType.UUID)
	@Column(name="session_id")
	private UUID id;
	
	@Column(name="session_token",
			nullable = false)
	private String token;
	
	@Column(name="expire_time",
			nullable = false)
	private LocalDateTime expireTime;
	
	@OneToOne
	@JoinColumn(
			name="user_id",
			nullable = false,
			referencedColumnName = "user_id"
	)
	private User user;
}
