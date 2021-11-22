package com.safetynetalert.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynetalert.dto.ChildDto;
import com.safetynetalert.dto.FloodedPersonByAddressDto;
import com.safetynetalert.dto.ListPersonDto;
import com.safetynetalert.dto.PersonDto;
import com.safetynetalert.dto.PersonInfoDto;
import com.safetynetalert.dto.PersonLivingAtAddressDto;
import com.safetynetalert.dto.PersonsAtAddressDto;
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
	public void list() throws Exception {
		
		// Arrange
		String firstName = "Sophia", lastName = "Zemicks";
		
		//Act
		List<Person> lPersons = personService.list();
		
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPersons.size());
		
		assertTrue(lPersons.stream().anyMatch(person ->firstName.equalsIgnoreCase(person.getFirstName()) &&
				lastName.equals(person.getLastName())));
		
	}
	
	@Test
	public void insert_newAndValidPerson_PersonExistsInPersonList() throws Exception {
		// Arrange
		Person person = new Person("Pichereau", "Elsa", "1511 Culver St",
				"Paris", "75017", "0603037826", "elpiche@gmail.com" );
			
		// Act
		Boolean isInserted = personService.insert(person);
		
		// Assert
		assertTrue(isInserted);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPerson.size());
		assertTrue(lPerson.stream().anyMatch(result -> person.getFirstName().equals(result.getFirstName()) &&
				person.getLastName().equals(result.getLastName())));
		
	}
	
	@Test
	public void insert_duplicateAndValidPerson_InsertionFails() throws Exception {
		
		// Arrange
		Person person = new Person("Elsa", "Pichereau", "1511 Culver St",
				"Paris", "75017", "0603037826", "elpiche@gmail.com" );
			
		// Act
		personService.insert(person);
		
		// Act
		Boolean isInserted = personService.insert(person);
	
		// Assert
		assertFalse(isInserted);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPerson.size());
	}
	
	@Test
	public void update_existingPerson_PersonUpdated() throws Exception {
		// Arrange
		
		Person person = new Person("Ali", "Faty", "8 Rue des Alizes",
				"Paris", "75010", "841-874-6512", "tenz@email.com" );
		personService.insert(person);
		
		person.setEmail("faty@email.com");
		
		// Act
		Boolean isUpdated = personService.update(person);
		
		// Assert
		
		assertTrue(isUpdated);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPerson.size());
		assertTrue(lPerson.stream().anyMatch(result -> person.getFirstName().equals(result.getFirstName()) &&
				person.getLastName().equals(result.getLastName()) && person.getEmail().equals("faty@email.com")));
		
	}
	
	@Test
	public void delete_existingPerson_Persondeleted() throws Exception {
		// Arrange
		insert_duplicateAndValidPerson_InsertionFails();
		Person person = new Person("Elsa", "Pichereau", "1511 Culver St",
				"Paris", "75017", "0603037826", "elpiche@gmail.com" );
		lPerson = personService.list();	
		// Act
		Boolean isDeleted = personService.delete("Elsa", "Pichereau");
		
		// Assert
		assertTrue(isDeleted);
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPerson.size());
		assertFalse(lPerson.contains(person));
		
	}
	
	@Test
	@Tag("PersonsCoveredByStationNumber")
	void getPersonsCoveredByStationNumber_StationNumber_personsCoverByStationNumber() throws Exception {
		// Arrange
		String stationNumber = "3";
		int nbAdult = 8;
		int ndChildren = 3;
		PersonDto personDto = new PersonDto();
		personDto.setFirstName("Tenley");
		personDto.setLastName("Boyd");
		personDto.setAddress("1509 Culver St");
		personDto.setPhone("841-874-6512");
		
		// Act
		ListPersonDto lPersonDto = personService.getPersonsCoveredByStationNumber(stationNumber);
			
		
		// Assert
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPersonDto.getListPersonDto());
		assertTrue(lPersonDto.getListPersonDto()
				.stream()
				.anyMatch(person -> person.getFirstName().equalsIgnoreCase(personDto.getFirstName()) &&
						person.getLastName().equals(personDto.getLastName()) && 
						person.getAddress().equals(personDto.getAddress())));
		assertEquals(nbAdult, lPersonDto.getNbAdults());
		assertEquals(ndChildren, lPersonDto.getNbChildren());
		
		 
	}
	
	@Test
	void calculatePersonAge_WrongDate_throwsException() {
		
		// Act
		ParseException exception = 
				Assertions.assertThrows(ParseException.class, () -> {
					personService.calculatePersonAge("Hello");});
		
		// assert
		Assertions.assertEquals("Unparseable date: \"Hello\"", exception.getMessage());
	}
	
	@Test
	@Tag("ChildrenLivingAtAddress")
	void getChildrenLivingAtAddress_address_childrenLivingAtAddress() throws Exception {
		// Arrange
		String address = "892 Downing Ct";
		int age = 4;
		ChildDto childDto = new ChildDto();
		childDto.setFirstName("Zach");
		childDto.setLastName("Zemicks");
		childDto.setAge(personService.calculatePersonAge("03/06/2017"));
		
		// Act
		PersonsAtAddressDto lchildrenLivingAtAddressDto = personService.getChildrenLivingAtAddress(address);			
		
		// Assert
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lchildrenLivingAtAddressDto);
		/*assertTrue(lchildrenLivingAtAddressDto
				.stream()
				.anyMatch(person -> person.getFirstName().equalsIgnoreCase(childDto.getFirstName()) &&
						person.getLastName().equals(childDto.getLastName()) 
						&& person.getAge().equals(childDto.getAge())));*/
		assertEquals(4, childDto.getAge());
		
	}
	
	@Test
	@Tag("PhoneNumbersList")
	void getPersonsPhoneNumberByStation_StationNumber_ListOfPhoneNumbers() throws Exception {
		// Arrange
		String stationNumber = "3";
		Person person = new Person();
		person.setPhone("841-874-8547");
		
		// Act
		List<String> lPhone = personService.getPersonsPhoneNumberByStation(stationNumber);
		
		// Assert
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPhone);
		assertFalse(lPhone.contains("841-874-8547"));
		assertTrue(lPhone.contains("841-874-6544"));
		assertTrue(lPhone.contains("841-874-6513"));
	}
	
	@Test
	@Tag("PersonsLivingAtAddress")
	void getPersonsLivingAtAddress_Address_personsLivingAtAddress() throws Exception {
		// Arrange
		String address = "1509 Culver St ";
		List<String> medication = new ArrayList<>();
		List<String> allergy = new ArrayList<>();
		int age = 4;
		PersonLivingAtAddressDto personLivingAtAddressDto = new PersonLivingAtAddressDto();
		personLivingAtAddressDto.setFirstName("Roger");
		personLivingAtAddressDto.setLastName("Boyd");
		personLivingAtAddressDto.setPhone("841-874-6512");
		personLivingAtAddressDto.setStation("3");
		personLivingAtAddressDto.setAge(age);
		personLivingAtAddressDto.setMedications(medication);
		personLivingAtAddressDto.setAllergies(allergy);
		// Act
		List<PersonLivingAtAddressDto> lPersonsLivingAtAddressDto = personService.getPersonsLivingAtAddress(address);
			
		
		// Assert
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPersonsLivingAtAddressDto);
		assertTrue(lPersonsLivingAtAddressDto.contains(personLivingAtAddressDto));
		assertEquals(5, lPersonsLivingAtAddressDto.size());
		 
	}
	
	@Test
	@Tag("FloodedPersonByAddress")
	void getFloodedPersonsByAddress_StationNumbers_floodedPersonsByAddress() throws Exception {
		// Arrange
		
		String address; // = "1509 Culver St ";
		List<String> stationNumbers = new ArrayList<>();
		stationNumbers.add("1");
		stationNumbers.add("2");
		stationNumbers.add("3");
		stationNumbers.add("4");
		List<String> medication = new ArrayList<>();
		medication.add("aznol:350mg");
		medication.add("hydrapermazol:100mg");
		List<String> allergy = new ArrayList<>();
		allergy.add("nillacilan");
		int age = 37;
		FloodedPersonByAddressDto floodedPersonByAddressDto = new FloodedPersonByAddressDto();
		floodedPersonByAddressDto.setFirstName("John");
		floodedPersonByAddressDto.setLastName("Boyd");
		floodedPersonByAddressDto.setAddress("1509 Culver St");
		floodedPersonByAddressDto.setPhone("841-874-6512");
		floodedPersonByAddressDto.setAge(age);
		floodedPersonByAddressDto.setMedications(medication);
		floodedPersonByAddressDto.setAllergies(allergy);
		
		// Act
		List<FloodedPersonByAddressDto> lFloodedPersonByAddressDto = personService.getFloodedPersonsByAddress(stationNumbers);
			
		
		// Assert
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lFloodedPersonByAddressDto);
		assertTrue(lFloodedPersonByAddressDto.contains(floodedPersonByAddressDto));
		assertEquals(23, lFloodedPersonByAddressDto.size());
	}
	
	@Test
	@Tag("PersonsInfo")
	void getPersonsInfo_firstName_lastName_personsInfo() throws Exception {
		// Arrange
		List<String> medication = new ArrayList<>();
		//medication.add("aznol:350mg");
		List<String> allergy = new ArrayList<>();
		List<PersonInfoDto> expectedPersonList = new ArrayList<>(); 
		
		PersonInfoDto personInfoDto1 = new PersonInfoDto();
		PersonInfoDto personInfoDto2 = new PersonInfoDto();
		PersonInfoDto personInfoDto3 = new PersonInfoDto();
		
		// First person to be added in assertedPersonList
		personInfoDto1.setFirstName("Brian");
		personInfoDto1.setLastName("Stelzer");
		personInfoDto1.setAddress("947 E. Rose Dr");
		personInfoDto1.setAge(46);
		personInfoDto1.setEmail("bstel@email.com");
		personInfoDto1.setMedications(Stream.of("ibupurin:200mg", "hydrapermazol:400mg").collect(Collectors.toList()));
		personInfoDto1.setAllergies(Stream.of("nillacilan").collect(Collectors.toList()));
		
		expectedPersonList.add(personInfoDto1);
		
		// Second person to be added in assertedPersonList
		personInfoDto2.setFirstName("Shawna");
		personInfoDto2.setLastName("Stelzer");
		personInfoDto2.setAddress("947 E. Rose Dr");
		personInfoDto2.setAge(41);
		personInfoDto2.setEmail("ssanw@email.com");
		personInfoDto2.setMedications(medication);
		personInfoDto2.setAllergies(allergy);
		
		expectedPersonList.add(personInfoDto2);
		
		// Third person to be added in assertedPersonList
		personInfoDto3.setFirstName("Kendrik");
		personInfoDto3.setLastName("Stelzer");
		personInfoDto3.setAddress("947 E. Rose Dr");
		personInfoDto3.setAge(7);
		personInfoDto3.setEmail("bstel@email.com");
		personInfoDto3.setMedications(Stream.of("noxidian:100mg", "pharmacol:2500mg").collect(Collectors.toList()));
		personInfoDto3.setAllergies(allergy);
		
		expectedPersonList.add(personInfoDto3);	

		// Act
		List<PersonInfoDto> lPersonsInfoDto = personService.getPersonsInfo("Shawna", "Stelzer");
			
		// Assert
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lPersonsInfoDto);
		assertEquals(3, lPersonsInfoDto.size());
		// assertEquals(expectedPersonList, lPersonsInfoDto);
		Assertions.assertIterableEquals(expectedPersonList, lPersonsInfoDto);
		assertTrue(lPersonsInfoDto.containsAll(expectedPersonList));
		 
	}
	
	@Test
	@Tag("CommunityEmailAddress")
	void getPersonsCommunityEmailByCity_city_ListOfEmail() throws Exception {
		// Arrange
		String city = "Culver";
		Person person = new Person();
		person.setEmail("email");
		
		
		// Act
		List<String> lEmails = personService.getPersonsCommunityEmailByCity(city);
			
		
		// Assert
		Assertions.assertNotEquals(Collections.EMPTY_LIST, lEmails);
		assertTrue(lEmails.contains("jaboyd@email.com"));
		assertTrue(lEmails.contains("tcoop@ymail.com"));
		assertTrue(lEmails.contains("gramps@email.com"));
	}
}

