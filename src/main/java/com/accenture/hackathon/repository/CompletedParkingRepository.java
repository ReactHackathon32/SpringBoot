package com.accenture.hackathon.repository;

import java.util.UUID;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.hackathon.entity.CompletedParkingEvent;

@Repository
public interface CompletedParkingRepository extends JpaRepository<CompletedParkingEvent, UUID>{

}
