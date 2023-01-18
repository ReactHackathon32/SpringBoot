package com.accenture.hackathon.model;

import java.util.ArrayList;

import java.util.List;

public class CarPark_Data {
private ArrayList <CarPark_Info> carpark_info;
private String carpark_number;
private String update_datetime;

public CarPark_Data() {
	super();
	// TODO Auto-generated constructor stub
}

public CarPark_Data(ArrayList<CarPark_Info> carpark_info, String carpark_number, String update_datetime) {
	super();
	this.carpark_info = carpark_info;
	this.carpark_number = carpark_number;
	this.update_datetime = update_datetime;
}

public ArrayList<CarPark_Info> getCarpark_info() {
	return carpark_info;
}

public void setCarpark_info(ArrayList<CarPark_Info> carpark_info) {
	this.carpark_info = carpark_info;
}

public String getCarpark_number() {
	return carpark_number;
}

public void setCarpark_number(String carpark_number) {
	this.carpark_number = carpark_number;
}

public String getUpdate_datetime() {
	return update_datetime;
}

public void setUpdate_datetime(String update_datetime) {
	this.update_datetime = update_datetime;
}

@Override
public String toString() {
	return "" + carpark_info + " carpark_number= " + carpark_number + ", update_datetime= "
			+ update_datetime + "";
}










}
