package com.accenture.hackathon.service;


import java.util.ArrayList;


import com.accenture.hackathon.model.CarPark_Data;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface DynamicPriceService {

	
public void generateCarParks();

public void updateDynamicPrice();

public  ArrayList<CarPark_Data>  getDataFromAvailableLotsAPI() throws JsonMappingException, JsonProcessingException ;
 
}



