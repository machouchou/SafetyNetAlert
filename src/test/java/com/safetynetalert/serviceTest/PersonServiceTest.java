package com.safetynetalert.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.safetynetalert.dao.IPersonDAO;
import com.safetynetalert.model.FireStation;
import com.safetynetalert.model.Person;
import com.safetynetalert.service.IPersonService;

@SpringBootTest
public class PersonServiceTest {
	
	@Autowired
	IPersonService personService;
	
	List<Person> lPerson;
	
	@BeforeEach
	public void config() {
		lPerson = personService.list();
	}
	
	@Test
	public void list() {
		
		/*
		List<Person> lPersons = new ArrayList<>();
		Person person = new Person();
		lPersons.add(person);
		
		when(JSONPersonDao.getPersonList()).thenReturn(lPersons);
		
		
		List
		assertEquals(lPersons.size(), lResult.size());
		*/
	}
	
	@Test
	public void insert_newAndValidPerson_PersonExistsInPersonList() {
		// Arrange
		Person person = new Person("Pichereau", "Elsa", "1511 Culver St",
				"Paris", "75017", "0603037826", "elpiche@gmail.com" );
			
		// Act
		Boolean isInserted = personService.insert(person);
		lPerson = personService.list();
		
		// Assert
		assertTrue(isInserted);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPerson.size());
		assertTrue(lPerson.stream().anyMatch(result -> person.getFirstName().equals(result.getFirstName()) &&
				person.getLastName().equals(result.getLastName())));
		
	}
	
	@Test
	public void insert_duplicateAndValidPerson_InsertionFails() {
		// Arrange
		insert_newAndValidPerson_PersonExistsInPersonList();
		
		Person person = new Person("Pichereau", "Elsa", "1511 Culver St",
				"Paris", "75017", "0603037826", "elpiche@gmail.com" );
			
		// Act
		Boolean isInserted = personService.insert(person);
				
		// Assert
		assertFalse(isInserted);
	}
}
