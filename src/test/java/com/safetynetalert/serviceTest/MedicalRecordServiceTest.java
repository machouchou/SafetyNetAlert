package com.safetynetalert.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynetalert.dao.JSONMedicalRecordDAO;
import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.service.IMedicalRecordService;

@SpringBootTest
public class MedicalRecordServiceTest {
	
	@Autowired
	IMedicalRecordService medicalRecordService;
	List<MedicalRecord> lMedicalRecords;
	
	@Autowired
	JSONMedicalRecordDAO dao;
	
	@BeforeEach
	public void config() {
		lMedicalRecords = medicalRecordService.list();
	}
	
	@Test
	public void list() {
		// Arrange
		String firstName = "Lily", lastName ="Cooper";
		
		// Act
		List<MedicalRecord> lResult = medicalRecordService.list();
		
		// Assert
		Assertions.assertNotEquals(Collections.EMPTY_LIST,lResult.size());
		assertTrue(lResult.stream().anyMatch(result -> firstName.equals(result.getFirstName()) &&
				lastName.equals(result.getLastName())));
		
	}
	
	@Test
	public void insert_newAndValidMedicalRecord_MedicalRecordInsertsInMedicalRecordList() {
		// Arrange
		List<String> medication = new ArrayList<>();
		List<String> allergy = new ArrayList<>();
		MedicalRecord medicalRecord = new MedicalRecord("Michel", "Toto", 
				"10/17/2020", medication, allergy);
		List<MedicalRecord> lMedicalRecords = medicalRecordService.list();
		
		// Act
		Boolean isInserted = medicalRecordService.insert(medicalRecord);
		
		// Assert
		assertTrue(isInserted);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lMedicalRecords);
		assertTrue(lMedicalRecords.contains(medicalRecord));
	}
		
	@Test
	public void insert_duplicateValidMedicalRecord_MedicalRecordInsertionIsImpossible( ) {
		// Arrange
		List<String> medication = new ArrayList<>();
		List<String> allergy = new ArrayList<>();
		MedicalRecord medicalRecord = new MedicalRecord("Foster", "Shepard", 
				"01/08/1980", medication, allergy);
		List<MedicalRecord> lMedicalRecords = medicalRecordService.list();
		
		// Act
		Boolean isInserted = medicalRecordService.insert(medicalRecord);
		
		// Assert
		assertFalse(isInserted);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lMedicalRecords);
		//assertTrue(lMedicalRecords.contains(medicalRecord));
		
	}
	
	@Test
	public void update_existingValidMedicalRecord_MedicalRecordUpdatedInList( ) {
		// Arrange
		List<String> medication = new ArrayList<>();
		List<String> allergy = new ArrayList<>();
		
		MedicalRecord medicalRecord = new MedicalRecord("Michel", "Carine", 
				"10/17/2020", medication, allergy);
		medicalRecordService.insert(medicalRecord);
		MedicalRecord updatedMedicalRecord = new MedicalRecord("Michel", "Carine", 
				"07/08/1982", medication, allergy);
		
		// Act
		Boolean isUpdated = medicalRecordService.update(updatedMedicalRecord);
		
		// Assert
		assertTrue(isUpdated);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lMedicalRecords);
		assertTrue(lMedicalRecords.contains(updatedMedicalRecord));
	}
	
	@Test
	public void delete_existingValidMedicalRecord_MedicalRecordDeletedFromList() {
		insert_newAndValidMedicalRecord_MedicalRecordInsertsInMedicalRecordList();
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName("firstName");
		medicalRecord.setLastName("lastName");
		List<MedicalRecord> lMedicalRecords = medicalRecordService.list();
		
		// Act
		Boolean isDeleted = medicalRecordService.delete("Michel", "Toto");
		
		// Assert
		assertTrue(isDeleted);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lMedicalRecords);
		assertFalse(lMedicalRecords.contains(medicalRecordService));
	}
}

