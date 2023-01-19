package com.accenture.hackathon.model;


import java.util.ArrayList;


public class OverallAvailableSlots{
	private ArrayList<CarPark_List> items;
	
	
	

	public OverallAvailableSlots() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OverallAvailableSlots(ArrayList<CarPark_List> items) {
		super();
		this.items = items;
	}

	public ArrayList<CarPark_List> getItems() {
		return items;
	}

	public void setItems(ArrayList<CarPark_List> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "AvailableSlotsWrapper  " + items + " ";
	}


	
	
	

}