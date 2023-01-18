package com.accenture.hackathon.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.hackathon.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	User findByEmail(String email);
	
	

}
