package com.accenture.hackathon.service;

import java.time.LocalDateTime;
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
			cp.setBasePrice(1.20f);
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

	
	
	public float calculateCost(LocalDateTime start, LocalDateTime end, float price) {
		
		
		
    return 0.0f;
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
			
		    float fraction = (float)available_Lots/ (float)total_Lots ;
		    float fractionLots = (float) Math.round(fraction * 10) / 10;
		    System.out.println("FractionLots: " + fractionLots);
		    
			float multiple = (1 -  fractionLots) * 4;
			float multiplyer = (float) Math.round(multiple * 10) / 10;
			System.out.println("multiplyer " + multiplyer);
			
			float dynamic =((cp.getBasePrice() * multiplyer) + cp.getBasePrice()) ;
			float dynamicPrice = (float)Math.round(dynamic * 10) / 10;
			System.out.println("dynamic price: " + dynamicPrice);
			System.out.println();
			
			cp.setTotalLots(total_Lots);
			cp.setAvailableLots(available_Lots);
			cp.setDynamicPrice(dynamicPrice);

			
			
			
	      if ((fractionLots > 0) || (fractionLots <= 1)) 
		
	        {

	    	   cp.getDynamicPrice();
			
		    }
	      
	       else if (fractionLots <= 0 ) 
	        
	       {

	    	   cp.getDynamicPrice( );
			
	        }
	      
			
		 
			carparkService.saveCarpark(cp);
	    }
		  System.out.println("updateDynamicPrice()");	
     }
  }

	


