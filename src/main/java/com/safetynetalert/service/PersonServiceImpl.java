package com.safetynetalert.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.dao.IPersonDAO;
import com.safetynetalert.dao.JSONFireStationDAO;
import com.safetynetalert.dao.JSONMedicalRecordDAO;
import com.safetynetalert.dto.ChildDto;
import com.safetynetalert.dto.ListPersonDto;
import com.safetynetalert.dto.ListPersonPhoneNumberDto;
import com.safetynetalert.dto.OtherPersonDto;
import com.safetynetalert.dto.PersonDto;
import com.safetynetalert.dto.PersonLivingAtAddressDto;
import com.safetynetalert.dto.PersonPhoneDto;
import com.safetynetalert.dto.PersonsAtAddressDto;
import com.safetynetalert.model.FireStation;
import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.model.Person;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonDAO personDao;

	@Autowired
	private JSONFireStationDAO fireStationDao;

	@Autowired
	private JSONMedicalRecordDAO medicalRecordDao;

	@Override
	public List<Person> list() {
		return this.personDao.getPersonList();
	}

	@Override
	public boolean insert(Person person) {
		return this.personDao.insert(person);
	}

	@Override
	public boolean update(Person person) {
		return this.personDao.update(person);
	}

	@Override
	public boolean delete(String lastname, String firstname) {
		return this.personDao.delete(lastname, firstname);
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

					person.setMedicalRecord(medicalRecordDao
							.getMedicalRecordBasedOnFirstAndLastName(person.getFirstName(), person.getLastName()));
					PersonDto personDto = new PersonDto(person.getFirstName(), person.getLastName(),
							person.getAddress(), person.getPhone());

					// Convertir birthDate récupérée sous forme de string en objet de type Date
					String birthDateAsString = person.getMedicalRecord().getBirthDate();

					try {
						int age = calculatePersonAge(birthDateAsString);

						setMinorOrAdultNumber(listPersonDto, age);

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

	private void setMinorOrAdultNumber(ListPersonDto listPersonDto, int age) {
		if (age < 18) {
			listPersonDto.setNbChildren(listPersonDto.getNbChildren() + 1);
		} else {
			listPersonDto.setNbAdults(listPersonDto.getNbAdults() + 1);
		}
	}

	private boolean isChild(Person person) {

		try {
			int personAge = calculatePersonAge(person.getMedicalRecord().getBirthDate());
			person.setAge(personAge);

			if (person.getAge() <= 18) {
				return true;
			}

		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private int calculatePersonAge(String birthDateAsString) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Calendar birthDate = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		birthDate.setTime(formatter.parse(birthDateAsString));
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

		return today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
	}

	@Override
	public PersonsAtAddressDto getChildrenLivingAtAddress(String address) {
		List<Person> personsAtSameAddress = personDao.getListPersonsByAddress(address);
		List<ChildDto> listChildrenDto = new ArrayList<>();
		List<OtherPersonDto> listOtherPersons = new ArrayList<>();

		for (Person person : personsAtSameAddress) {
			person.setMedicalRecord(medicalRecordDao.getMedicalRecordBasedOnFirstAndLastName(person.getFirstName(),
					person.getLastName()));

			if (isChild(person)) {
				ChildDto childDto = new ChildDto(person.getFirstName(), person.getLastName(), person.getAge());
				listChildrenDto.add(childDto);
			} else {
				OtherPersonDto otherPersonDto = new OtherPersonDto(person.getFirstName(), person.getLastName());
				listOtherPersons.add(otherPersonDto);
			}
		}

		return new PersonsAtAddressDto(listChildrenDto, listOtherPersons);
	}

	@Override
	public List<PersonPhoneDto> getPersonsPhoneNumberByStation(String stationNumber) {

		List<FireStation> fireStationsByNumber = fireStationDao.getFireStations(stationNumber);

		List<Person> persons = new ArrayList<>();
		List<PersonPhoneDto> phoneNumbersCoveredBySameStationNumber = new ArrayList<>();

		for (FireStation fireStation : fireStationsByNumber) {
			String address = fireStation.getAddress();
			persons.addAll(personDao.getListPersonsByAddress(address));
		}
		for (Person person : persons) {
			// phoneNumbersCoveredBySameStationNumber.add(person.getFirstName(); + " " +
			// person.getLastName() + " : " + person.getPhone());
			PersonPhoneDto personPhoneDto = new PersonPhoneDto(person.getFirstName(), person.getLastName(),
					person.getPhone());

			phoneNumbersCoveredBySameStationNumber.add(personPhoneDto);
		}

		return phoneNumbersCoveredBySameStationNumber;
	}

	@Override
	public List<PersonLivingAtAddressDto> getPersonsLivingAtAddress(String address) {
		
		List<FireStation> fireStationsAddress = fireStationDao.getFireStationAddress(address);
		
		List<PersonLivingAtAddressDto> personsLivingAtAddress = new ArrayList<>();
		
		try {
						
			for (Person person : personDao.getListPersonsByAddress(address)) {
				
				for (FireStation fireStation : fireStationsAddress) {
					
					if (person.getAddress().equalsIgnoreCase(fireStation.getAddress())) {
						person.setFireStation(fireStation);
					}
				
					person.setMedicalRecord(medicalRecordDao.getMedicalRecordBasedOnFirstAndLastName
								(person.getFirstName(), person.getLastName()));
					
					PersonLivingAtAddressDto personLivingAtAddressDto = constructPersonLivingAtAddressDto(person);
					
					personsLivingAtAddress.add(personLivingAtAddressDto);
				}
			}
				
			} catch (ParseException e) {
				
			}
			return personsLivingAtAddress;
		}

	private PersonLivingAtAddressDto constructPersonLivingAtAddressDto(Person person) throws ParseException {
		
		PersonLivingAtAddressDto personLivingAtAddressDto = new PersonLivingAtAddressDto();
		personLivingAtAddressDto.setFirstName(person.getFirstName());
		personLivingAtAddressDto.setLastName(person.getLastName());
		personLivingAtAddressDto.setPhone(person.getPhone());
		personLivingAtAddressDto.setStation(person.getFireStation().getStation());
		personLivingAtAddressDto.setAge(calculatePersonAge(person.getMedicalRecord().getBirthDate()));
		personLivingAtAddressDto.setMedications(person.getMedicalRecord().getMedications());
		personLivingAtAddressDto.setAllergies(person.getMedicalRecord().getAllergies());
		
		return personLivingAtAddressDto;
	}
}
