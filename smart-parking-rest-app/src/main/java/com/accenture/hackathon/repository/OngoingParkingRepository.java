package com.accenture.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.hackathon.entity.OngoingParkingEvent;

@Repository
public interface OngoingParkingRepository extends JpaRepository<OngoingParkingEvent, Long>{

}
