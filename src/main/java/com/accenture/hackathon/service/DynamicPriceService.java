package com.accenture.hackathon.service;


import java.time.LocalDateTime;

import java.util.ArrayList;


import com.accenture.hackathon.model.CarPark_Data;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface DynamicPriceService {

	
public void generateCarParks();

public void updateDynamicPrice();

public void scheduleEvent();

public float roundPrice(float price);

public  ArrayList<CarPark_Data>  getDataFromAvailableLotsAPI() throws JsonMappingException, JsonProcessingException ;

float calculateCost(LocalDateTime start, LocalDateTime end, float price) ;
}



