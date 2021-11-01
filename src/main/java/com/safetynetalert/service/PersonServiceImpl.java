package com.safetynetalert.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.dao.IPersonDAO;
import com.safetynetalert.dao.JSONFireStationDAO;
import com.safetynetalert.dao.JSONMedicalRecordDAO;
import com.safetynetalert.dto.ListPersonDto;
import com.safetynetalert.dto.PersonDto;
import com.safetynetalert.model.FireStation;
import com.safetynetalert.model.Person;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonDAO personDAO;

	@Autowired
	private JSONFireStationDAO fireStationDao;

	@Autowired
	private JSONMedicalRecordDAO medicalRecordDao;

	@Override
	public List<Person> list() {
		return this.personDAO.getPersonList();
	}

	@Override
	public boolean insert(Person person) {
		return this.personDAO.insert(person);
	}

	@Override
	public boolean update(Person person) {
		return this.personDAO.update(person);
	}

	@Override
	public boolean delete(String lastname, String firstname) {
		return this.personDAO.delete(lastname, firstname);
	}

	@Override
	public ListPersonDto getPersonsCoveredByStationNumber(String stationNumber) {

		List<FireStation> fireStationsByNumber = fireStationDao.getFireStations(stationNumber);
		ListPersonDto listPersonDto = new ListPersonDto();
		listPersonDto.setNbChildren(0);
		listPersonDto.setNbAdults(0);
		
		for (Person person : list()) {
			for (FireStation fireStation : fireStationsByNumber) {
				if (person.getAddress().equalsIgnoreCase(fireStation.getAddress())) {
					
					person.setMedicalRecord(medicalRecordDao.getMedicalRecordBasedOnFirstAndLastName(person.getFirstName(), person.getLastName()));
					PersonDto personDto = new PersonDto(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
					
					// Convertir birthDate récupérée sous forme de string en objet de type Date
					String birthDateAsString = person.getMedicalRecord().getBirthDate();
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					try {
						int age = calculatePersonAge(birthDateAsString, formatter);
					    
					    isAdult(listPersonDto, age);
					    					    
					    listPersonDto.getListPersonDto().add(personDto);
					} catch (ParseException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return listPersonDto;
	}

	private void isAdult(ListPersonDto listPersonDto, int age) {
		if (age < 18) {
			listPersonDto.setNbChildren(listPersonDto.getNbChildren() + 1);
		} else {
			listPersonDto.setNbAdults(listPersonDto.getNbAdults() + 1);
		}
	}

	private int calculatePersonAge(String birthDateAsString, DateFormat formatter) throws ParseException {
		Calendar birthDate = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris")); 
		birthDate.setTime(formatter.parse(birthDateAsString));
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
	
		return today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
	}

}
