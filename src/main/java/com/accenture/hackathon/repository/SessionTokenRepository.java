package com.accenture.hackathon.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.hackathon.entity.SessionToken;

@Repository
public interface SessionTokenRepository extends JpaRepository<SessionToken, UUID>{

	Optional<SessionToken> getByToken(String token);
}
