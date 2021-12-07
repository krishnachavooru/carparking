package com.example.carparking.schedular;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.example.carparking.model.ParkManager;
import com.example.carparking.service.BookParking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	@Autowired
	private BookParking bookParking;
	
	@Scheduled(cron = "${test.cron}")
	public void run() {
	    System.out.println("Current time is :: " + LocalDateTime.now());
	    
	    Map<String, ParkManager> map = bookParking.getAllParkingDetails();
	    
	    map.forEach((key,value)->{
	    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	    	
	    	System.out.println(value.getCurrentDateTime().plusHours(1).format(formatter));
	    	System.out.println(LocalDateTime.now().format(formatter));
	    	
	    	if(value.getCurrentDateTime().plusHours(value.getNumberOfHours()).format(formatter).equals(LocalDateTime.now().format(formatter))) {
	    		System.out.println("Deallocating slots...");
	    		
	    		bookParking.deallocateParking(value);
	    		
	    	}
	    });
	    
	    System.out.println("Scaned all elements...");
	    
	}

}
