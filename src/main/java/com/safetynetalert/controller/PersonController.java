package com.safetynetalert.controller;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetalert.dto.ChildDto;
import com.safetynetalert.dto.ListPersonPhoneNumberDto;
import com.safetynetalert.dto.ListPersonDto;
import com.safetynetalert.dto.PersonDto;
import com.safetynetalert.dto.PersonLivingAtAddressDto;
import com.safetynetalert.dto.PersonPhoneDto;
import com.safetynetalert.dto.PersonsAtAddressDto;
import com.safetynetalert.model.Person;
import com.safetynetalert.service.IPersonService;

@RestController
public class PersonController {

	@Autowired
	private IPersonService personService;

	@GetMapping("person")
	public List<Person> list() {
		return this.personService.list();
		 
	}

	@PostMapping("person")
	public boolean insert(@NotNull @RequestBody Person person) {
		return this.personService.insert(person);
	}

	@PutMapping("person")
	public boolean update(@NotNull @RequestBody final Person person) {
		return this.personService.update(person);
	}

	@DeleteMapping("person")
	public boolean delete(@RequestParam final String lastName, @RequestParam final String firstName) {
		return this.personService.delete(lastName, firstName);
	}
	@GetMapping("firestation")
	public ListPersonDto listPersonCoveredByStationNumber(@RequestParam final String stationNumber) {
		return this.personService.getPersonsCoveredByStationNumber(stationNumber);
	}
	@GetMapping("childAlert")
	public PersonsAtAddressDto listChildLivingAtAddress(@RequestParam final String address) {
		return this.personService.getChildrenLivingAtAddress(address);
	}
	@GetMapping("phoneAlert")
	public List<PersonPhoneDto> listPersonPhoneNumberByStation(@RequestParam final String firestation) {
		return this.personService.getPersonsPhoneNumberByStation(firestation);
	}
	@GetMapping("fire")
	public List<PersonLivingAtAddressDto> listPersonsLivingAtAddress(@RequestParam final String address) {
		return this.personService.getPersonsLivingAtAddress(address);
	}
}
