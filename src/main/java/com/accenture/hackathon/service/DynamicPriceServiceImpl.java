package com.accenture.hackathon.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.model.CarPark_Data;
import com.accenture.hackathon.model.OverallAvailableSlots;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DynamicPriceServiceImpl implements DynamicPriceService {

	@Autowired
	private CarparkService carparkService;

	@Override
	public void generateCarParks() {
     System.out.println("generateCarParks()");
		ArrayList<CarPark_Data> carPark_DataList = new ArrayList<CarPark_Data>();

		try {
			carPark_DataList = getDataFromAvailableLotsAPI();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 100; i++) {
			Carpark cp = new Carpark();

			cp.setCarparkName(carPark_DataList.get(i).getCarpark_number());
			cp.setBasePrice(3.00f);
			cp.setDynamicPrice(3.00f);
		
			cp.setTotalLots(0);
			cp.setAvailableLots(0);
			carparkService.saveCarpark(cp);

		}

	}

	@Scheduled(cron = "*/30 * * * *")
	@Override
	public ArrayList<CarPark_Data> getDataFromAvailableLotsAPI() throws JsonMappingException, JsonProcessingException {

		String url = "https://api.data.gov.sg/v1/transport/carpark-availability";
		RestTemplate template = new RestTemplate();

		String result = template.getForObject(url, String.class);

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(result, OverallAvailableSlots.class).getItems().get(0).getCarpark_data();

	}

	@Override
	public void updateDynamicPrice() {
		  System.out.println("updateDynamicPrice()");
    ArrayList<CarPark_Data> carPark_DataList = new ArrayList<CarPark_Data>() ;

    try {
	carPark_DataList = getDataFromAvailableLotsAPI();
    } catch (JsonMappingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    } catch (JsonProcessingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	
     }



		for (int i = 0; i < 100; i++) {
			
			// set available lots/ set total lots  from carpark data list
			// from this data cal dynamic price from total lots and set in dynamic price
			
			Carpark cp = carparkService.fetchCarparkbyName(carPark_DataList.get(i).getCarpark_number());
			
	
		    int total_Lots = Integer.parseInt(carPark_DataList.get(i).getCarpark_info().get(0).getTotal_lots());
			int available_Lots = Integer.parseInt(carPark_DataList.get(i).getCarpark_info().get(0).getLots_available());
			System.out.println("TotalLots: " + total_Lots);
			System.out.println("AvailableLots: " + available_Lots);
		    float fractionLots = (float)available_Lots/ (float)total_Lots ;
//		    double fractionLots = Math.round(fraction * 10.0) / 10.0;
		    System.out.println("FractionLots: " + fractionLots);
			float multiplyer = 1 -  fractionLots;
//			double multiplyer = Math.round(multiple * 10.0) / 10;
			System.out.println("multiplyer " + multiplyer);
			float dynamicPrice =((cp.getBasePrice() * multiplyer) + cp.getBasePrice()) ;
//			double dynamicPrice =Math.round(multiple * 10.0) / 10;
			System.out.println("dynamic price: " + dynamicPrice);
			System.out.println();
			cp.setTotalLots(total_Lots);
			cp.setAvailableLots(available_Lots);
			cp.setDynamicPrice(dynamicPrice);
//			cp.setBasePrice(3.00f);
			
			
			
	      if ((fractionLots > 0) || (fractionLots <= 1)) 
		
	        {
//	    	   cp.setDynamicPrice(dynamicPrice);
	    	   cp.getDynamicPrice();
			
		    }
	      
	       else if (fractionLots <= 0 ) 
	        
	       {
//	    	   cp.setDynamicPrice(0.00f);
	    	   cp.getDynamicPrice( );
			
	        }
	      
			
//	       else if ((fractionLots > 0.1) || (fractionLots <= 0.3)) 
//	        
//	       {
//	    	   cp.setDynamicPrice(cp.getBasePrice()* multiplyer);
//	    	   cp.getDynamicPrice();
//			
//	        }
//	       else if ((fractionLots  > 0.3) || (fractionLots <= 0.5))
//	        {
//	    	   cp.setDynamicPrice(cp.getBasePrice()* multiplyer);
//	    	   cp.getDynamicPrice();
//	         }
//		   else if ((fractionLots > 0.5) || (fractionLots <= 1)) 
//		   {
//			   cp.setDynamicPrice(cp.getBasePrice()* multiplyer);
//	    	   cp.getDynamicPrice();
//			
//		    }
		 
			carparkService.saveCarpark(cp);
	    }
		  System.out.println("updateDynamicPrice()");	
     }
  }

	

//if (cp.getAvailableLots() / cp.getTotalLots() <= 0) {
//System.out.println("Parking Lot is Full");
//System.out.println(cp.getCarparkName());
//System.out.println(cp.getTotalLots());
//System.out.println(cp.getAvailableLots());
//
//}
//else if ((cp.getAvailableLots() / cp.getTotalLots() > 0)
//	|| (cp.getAvailableLots() / cp.getTotalLots() <= 0.1)) 
//{
//System.out.println("Parking Fee is $" + (cp.getBasePrice() + 6.00f == cp.getDynamicPrice()));
//System.out.println(cp.getCarparkName());
//System.out.println(cp.getDynamicPrice());
//System.out.println(cp.getTotalLots());
//System.out.println(cp.getAvailableLots());
//} 
//else if ((cp.getAvailableLots() / cp.getTotalLots() > 0.1)
//	|| (cp.getAvailableLots() / cp.getTotalLots() <= 0.3)) {
//System.out.println("Parking Fee is $" + (cp.getBasePrice() + 4.00f == cp.getDynamicPrice()));
//System.out.println(cp.getCarparkName());
//System.out.println(cp.getDynamicPrice());
//System.out.println(cp.getTotalLots());
//System.out.println(cp.getAvailableLots());
//
//} 
//else if ((cp.getAvailableLots() / cp.getTotalLots() > 0.3)
//	|| (cp.getAvailableLots() / cp.getTotalLots() <= 0.5)) {
//System.out.println("Parking Fee is $" + (cp.getBasePrice() + 2.00f == cp.getDynamicPrice()));
//System.out.println(cp.getCarparkName());
//System.out.println(cp.getDynamicPrice());
//System.out.println(cp.getTotalLots());
//System.out.println(cp.getAvailableLots());
//
//} 
//else if ((cp.getAvailableLots() / cp.getTotalLots() > 0.5)
//	|| (cp.getAvailableLots() / cp.getTotalLots() <= 1)) {
//System.out.println("Parking Fee is $" + (cp.getBasePrice() == cp.getDynamicPrice()));
//System.out.println(cp.getCarparkName());
//System.out.println(cp.getDynamicPrice());
//System.out.println(cp.getTotalLots());
//System.out.println(cp.getAvailableLots());
//
//}
