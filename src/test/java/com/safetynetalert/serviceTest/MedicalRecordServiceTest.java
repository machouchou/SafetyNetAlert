package com.safetynetalert.serviceTest;

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

import com.safetynetalert.dao.IMedicalRecordDAO;
import com.safetynetalert.dao.JSONMedicalRecordDAO;
import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.service.IMedicalRecordService;

@SpringBootTest
public class MedicalRecordServiceTest {
	
	@Autowired
	IMedicalRecordService medicalRecordService;
	
	@Autowired
	JSONMedicalRecordDAO dao;
	
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

}
