package com.safetynetalert.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynetalert.dao.JSONFireStationDAO;
import com.safetynetalert.model.FireStation;
import com.safetynetalert.service.IFireStationService;

@SpringBootTest
public class FireStationServiceTest {
	
	@Autowired
	IFireStationService fireStationService;
	
	List<FireStation> lFireStation;
	
	@Autowired
	JSONFireStationDAO dao ;
	
	@BeforeEach
	public void config() {
		lFireStation = fireStationService.getFireStations();
	}
	
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
	
	@Test
public void insert_newAndValidFirestation_FireStationExistsInFireStationsList() {
		// Arrange
		FireStation fireStation = new FireStation("1511 Culver St", "5");
		lFireStation = fireStationService.getFireStations();
		// Act
		Boolean isInserted = fireStationService.insert(fireStation);
		
		// Assert
		assertTrue(isInserted);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lFireStation.size());
		assertTrue(lFireStation.contains(fireStation)); 
		
	}

	@Test
	public void insert_duplicate_ValidFirestation_InsertionNotPossible() {
			// Arrange
			FireStation fireStation = new FireStation("1511 Culver St", "5");
			lFireStation = fireStationService.getFireStations();
			// Act
			
			fireStationService.insert(fireStation);
			
			Boolean isInserted = fireStationService.insert(fireStation);
			
			// Assert
			assertFalse(isInserted);
			Assertions.assertNotEquals(Collections.EMPTY_LIST, lFireStation.size());
			assertTrue(lFireStation.contains(fireStation)); 
			
		}
	
	@Test
	public void update_existingFireStation_UpdateFireStationSuccess() {
			// Arrange
			FireStation fireStation = new FireStation("1509 Culver St", "5");
			lFireStation = fireStationService.getFireStations();
			// Act
			
			Boolean isUpdated = fireStationService.update(fireStation);
			
			// Assert
			assertTrue(isUpdated);
			Assertions.assertNotEquals(Collections.EMPTY_LIST, lFireStation.size());
			assertTrue(lFireStation.contains(fireStation)); 
			
		}
	@Test
	public void delete_existingFireStation_deleteFireStation() {
			// Arrange
			FireStation fireStation = new FireStation("29 15th St", "2");
			List<FireStation> lFireStation = fireStationService.getFireStations();
			// Act
			
			Boolean isDeleted = fireStationService.delete(fireStation);
			
			// Assert
			assertTrue(isDeleted);
			Assertions.assertNotEquals(Collections.EMPTY_LIST, lFireStation.size());
			assertFalse(lFireStation.contains(fireStation)); 
			
		}
}
