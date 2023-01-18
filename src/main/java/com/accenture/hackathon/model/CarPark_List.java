package com.accenture.hackathon.model;

import java.util.ArrayList;
import java.util.List;

public class CarPark_List {
	
	private String timestamp;
    private ArrayList<CarPark_Data> carpark_data;
    

    
    
	public CarPark_List() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CarPark_List(String timestamp, ArrayList<CarPark_Data> carpark_data) {
		super();
		this.timestamp = timestamp;
		this.carpark_data = carpark_data;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public ArrayList<CarPark_Data> getCarpark_data() {
		return carpark_data;
	}
	public void setCarpark_data(ArrayList<CarPark_Data> carpark_data) {
		this.carpark_data = carpark_data;
	}


	@Override
	public String toString() {
		return " timestamp= " + timestamp + carpark_data + "";
	}
    
 
    
}
