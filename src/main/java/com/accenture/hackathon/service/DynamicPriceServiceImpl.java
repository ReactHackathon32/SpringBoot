package com.accenture.hackathon.service;

<<<<<<< HEAD
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.hackathon.entity.Carpark;
import com.accenture.hackathon.model.CarPark_Data;
import com.accenture.hackathon.model.CarPark_Info;
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
			Carpark cp = new Carpark();
			
			cp.setCarparkName(carPark_DataList.get(i).getCarpark_number());
			cp.setBasePrice(3.00f);
			cp.setDynamicPrice(3.00f);
			cp.setTotalLots(0);
			cp.setAvailableLots(0);

			carparkService.saveCarpark(cp);

		}
			
	}

	
	@Scheduled(cron="*/30 * * * *")
	@Override
	public ArrayList<CarPark_Data> getDataFromAvailableLotsAPI() throws JsonMappingException, JsonProcessingException {

		String url = "https://api.data.gov.sg/v1/transport/carpark-availability";
		RestTemplate template = new RestTemplate();

		String result = template.getForObject(url, String.class);

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(result, OverallAvailableSlots.class).getItems().get(0).getCarpark_data();

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
=======
public class DynamicPriceServiceImpl {

}
>>>>>>> 99b65180517c4b1d1044fd66bf2bd26161f51b55
