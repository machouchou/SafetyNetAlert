package com.safetynetalert.service;

import java.util.List;
import java.util.Set;

import com.safetynetalert.dto.ListPersonDto;
import com.safetynetalert.dto.PersonLivingAtAddressDto;
import com.safetynetalert.dto.PersonPhoneDto;
import com.safetynetalert.dto.PersonsAtAddressDto;
import com.safetynetalert.model.Person;

public interface IPersonService {

	public List<Person> list();
	
	public boolean insert(final Person person);
	
	public boolean update(final Person person);
	
	public boolean delete(final String lastname, final String firstname);

	public ListPersonDto getPersonsCoveredByStationNumber(final String stationNumber);

	public PersonsAtAddressDto getChildrenLivingAtAddress(final String address);

	public List<PersonPhoneDto> getPersonsPhoneNumberByStation(final String stationNumber);

	public List<PersonLivingAtAddressDto> getPersonsLivingAtAddress(final String address);
	
}
