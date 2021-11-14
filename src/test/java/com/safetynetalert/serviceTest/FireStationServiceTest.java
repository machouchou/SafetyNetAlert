package com.safetynetalert.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynetalert.dao.IFireStationDAO;
import com.safetynetalert.dao.JSONFireStationDAO;
import com.safetynetalert.model.FireStation;
import com.safetynetalert.service.IFireStationService;

@SpringBootTest
public class FireStationServiceTest {
	
	@Autowired
	IFireStationService fireStationService;
	
	@Autowired
	JSONFireStationDAO dao ;
	
	@Test
	public void getFireStations_validFirestationExistsInFireStationsList() {
		
		FireStation fireStation = new FireStation("1509 Culver St", "3");
				
		List<FireStation> lResult = fireStationService.getFireStations();
				
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lResult.size());
		assertTrue(lResult.contains(fireStation));
		
	}
	
	@Test
	public void getFireStations_validFirestationDoesNotExistsInFireStationsList() {
		
		FireStation fireStation = new FireStation("1511 Culver St", "5");
				
		List<FireStation> lResult = fireStationService.getFireStations();
				
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lResult.size());
		assertFalse(lResult.contains(fireStation));
		
	}

}
