package com.accenture.hackathon.repository;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.hackathon.entity.OngoingParkingEvent;
import com.accenture.hackathon.entity.User;

@Repository
public interface OngoingParkingRepository extends JpaRepository<OngoingParkingEvent, UUID>{

	Optional<OngoingParkingEvent> findByUser(User user);

}
