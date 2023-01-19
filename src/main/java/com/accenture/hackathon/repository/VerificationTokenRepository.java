package com.accenture.hackathon.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.hackathon.entity.User;
import com.accenture.hackathon.entity.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, UUID>{

	Optional<VerificationToken> findByToken(String token);

	Optional<VerificationToken> findByUser(User user);

	void deleteByUser(User user);
}
