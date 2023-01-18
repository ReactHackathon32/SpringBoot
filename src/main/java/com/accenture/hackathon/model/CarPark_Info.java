package com.accenture.hackathon.model;

public class CarPark_Info {
	private String total_lots;
	private String lot_type;
	private String lots_available;
	
	
	
	public CarPark_Info() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CarPark_Info(String total_lots, String lot_type, String lots_available) {
		super();
		this.total_lots = total_lots;
		this.lot_type = lot_type;
		this.lots_available = lots_available;
	}
	
	
	public String getTotal_lots() {
		return total_lots;
	}
	public void setTotal_lots(String total_lots) {
		this.total_lots = total_lots;
	}
	public String getLot_type() {
		return lot_type;
	}
	public void setLot_type(String lot_type) {
		this.lot_type = lot_type;
	}
	public String getLots_available() {
		return lots_available;
	}
	public void setLots_available(String lots_available) {
		this.lots_available = lots_available;
	}


	@Override
	public String toString() {
		return "total_lots=" + total_lots + "" + " lots_available="
				+ lots_available + "";
	}



	
	

}
