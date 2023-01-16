package com.accenture.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.hackathon.entity.Carpark;

@Repository
public interface CarparkRepository extends JpaRepository<Carpark, Long>{

}
