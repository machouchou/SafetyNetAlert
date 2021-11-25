package com.safetynetalert.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetalert.dto.FloodedPersonByAddressDto;
import com.safetynetalert.dto.ListPersonDto;
import com.safetynetalert.dto.PersonInfoDto;
import com.safetynetalert.dto.PersonLivingAtAddressDto;
import com.safetynetalert.dto.PersonsAtAddressDto;
import com.safetynetalert.model.Person;
import com.safetynetalert.service.IPersonService;

@RestController
public class PersonController {
	
	static final Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private IPersonService personService;

	@GetMapping("/person")
	public List<Person> list() {
		logger.info("list()");
		return this.personService.list();
	}

	@PostMapping("/person")
	public boolean insert(@NotNull @RequestBody Person person) {
		logger.info("POST /person called");
		return this.personService.insert(person);
	}

	@PutMapping("/person")
	public boolean update(@NotNull @RequestBody final Person person) {
		logger.info("PUT /person called");
		return this.personService.update(person);
	}

	@DeleteMapping("/person")
	public boolean delete(@RequestParam final String firstName, @RequestParam final String lastName) {
		logger.info("DELETE /person called");
		return this.personService.delete(firstName, lastName);
	}
	
	@GetMapping("firestation")
	public ListPersonDto listPersonCoveredByStationNumber(@RequestParam final String stationNumber) {
		logger.info("GET /firestation called");
		return this.personService.getPersonsCoveredByStationNumber(stationNumber);
	}
	
	@GetMapping("childAlert")
	public PersonsAtAddressDto listChildLivingAtAddress(@RequestParam final String address) {
		logger.info("GET /childAlert called");
		return this.personService.getChildrenLivingAtAddress(address);
	}
	
	@GetMapping("phoneAlert")
	public List<String> listPersonPhoneNumberByStation(@RequestParam final String firestation) {
		logger.info("GET /phoneAlert called");
		return this.personService.getPersonsPhoneNumberByStation(firestation);
	}
	
	@GetMapping("fire")
	public List<PersonLivingAtAddressDto> listPersonsLivingAtAddress(@RequestParam final String address) {
		logger.info("GET /fire called");
		return this.personService.getPersonsLivingAtAddress(address);
	}
	
	@GetMapping("flood/stations")
	public List<FloodedPersonByAddressDto> listFloodedPersonsBytAddress(@RequestParam final List<String> stations) {
		logger.info("GET /flood/stations called");
		return this.personService.getFloodedPersonsByAddress(stations);
	}
	
	@GetMapping("personInfo")
	public List<PersonInfoDto> listPersonsInfo(@RequestParam final String firstName, @RequestParam final String lastName ) {
		logger.info("GET /personInfo called");
		return this.personService.getPersonsInfo(firstName, lastName);
	}
	
	@GetMapping("communityEmail")
	public List<String> listPersonsCommunityEmailByCity(@RequestParam final String city ) {
		logger.info("GET /communityEmail called");
		return this.personService.getPersonsCommunityEmailByCity(city);
	}
}