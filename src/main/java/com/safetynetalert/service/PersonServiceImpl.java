package com.safetynetalert.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.config.AppConfig;
import com.safetynetalert.dao.IPersonDAO;
import com.safetynetalert.dao.JSONFireStationDAO;
import com.safetynetalert.dao.JSONMedicalRecordDAO;
import com.safetynetalert.dto.ChildDto;
import com.safetynetalert.dto.FloodedPersonByAddressDto;
import com.safetynetalert.dto.ListPersonDto;
import com.safetynetalert.dto.OtherPersonDto;
import com.safetynetalert.dto.PersonDto;
import com.safetynetalert.dto.PersonInfoDto;
import com.safetynetalert.dto.PersonLivingAtAddressDto;
import com.safetynetalert.dto.PersonsAtAddressDto;
import com.safetynetalert.model.FireStation;
import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.model.Person;

@Service
public class PersonServiceImpl implements IPersonService {
	
	static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);

	@Autowired
	private IPersonDAO personDao;

	@Autowired
	private JSONFireStationDAO fireStationDao;

	@Autowired
	private JSONMedicalRecordDAO medicalRecordDao;
	
	@Override
	public List<Person> list() {
		logger.debug("list()");
		return personDao.getPersonList();
	}
		
	@Override
	public boolean insert(Person person) {
		logger.debug("insert(Person : {})",  person);
		
		boolean isDuplicatePerson = list().stream().anyMatch(p -> p.getFirstName().equalsIgnoreCase(person.getFirstName()) &&
				p.getLastName().equalsIgnoreCase(person.getLastName()));
			
		if (isDuplicatePerson) {
			logger.error("This {} already exists, insertion is impossible.", person);
			return false;
		}
		
		return this.personDao.insert(person);
	}

	@Override
	public boolean update(Person person) {
		logger.debug("update(Person :  {})",  person);
		
		return this.personDao.update(person);
	}

	@Override
	public boolean delete(String firstName, String lastName) {
		logger.debug("delete(firstName : {}, lastName : {})", firstName, lastName);
		
		return this.personDao.delete(firstName, lastName);
	}

	@Override
	public ListPersonDto getPersonsCoveredByStationNumber(String stationNumber) {
		logger.debug("getPersonsCoveredByStationNumber(stationNumber : {})", stationNumber);
		
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
					String birthDateAsString = null;
					
					if (person.getMedicalRecord() != null) {
						birthDateAsString = person.getMedicalRecord().getBirthDate();
					}

					try {
						if (birthDateAsString != null) {
							int age = calculatePersonAge(birthDateAsString);
							setMinorOrAdultNumber(listPersonDto, age);
						}

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
		logger.debug("setMinorOrAdultNumber(listPersonDto : {}, age : {})", listPersonDto, age);
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

	public int calculatePersonAge(String birthDateAsString) throws ParseException {
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
	public List<String> getPersonsPhoneNumberByStation(String stationNumber) {

		List<FireStation> fireStationsByNumber = fireStationDao.getFireStations(stationNumber);

		List<Person> persons = new ArrayList<>();
		List<String> phoneNumbersCoveredBySameStationNumber = new ArrayList<>();

		for (FireStation fireStation : fireStationsByNumber) {
			String address = fireStation.getAddress();
			persons.addAll(personDao.getListPersonsByAddress(address));
		}
		for (Person person : persons) {
			// phoneNumbersCoveredBySameStationNumber.add(person.getFirstName(); + " " +
			// person.getLastName() + " : " + person.getPhone());
			String phoneNumber = person.getPhone();

			phoneNumbersCoveredBySameStationNumber.add(phoneNumber);
		}

		return phoneNumbersCoveredBySameStationNumber.stream().distinct().collect(Collectors.toList());
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

	@Override
	public List<FloodedPersonByAddressDto> getFloodedPersonsByAddress(List<String> stationNumbers) {
		
		List<Person> persons = new ArrayList<>();
		List<FloodedPersonByAddressDto> floodedPersonsByAddress = new ArrayList<>();
		
		for (String stationNumber : stationNumbers) {
			List<String> addresses = new ArrayList<String>();
					addresses= fireStationDao.getAddressesCoveredByAStationNumber(stationNumber);
			
			for (String address : addresses) {
				persons.addAll(personDao.getListPersonsByAddress(address));
			}
		}
		
		for (Person person : persons) {
				
			person.setMedicalRecord(medicalRecordDao.getMedicalRecordBasedOnFirstAndLastName
					(person.getFirstName(), person.getLastName()));
			
			try {
				FloodedPersonByAddressDto floodedPersonByAddressDto = constructFloodedPersonByAddressDto(person);
				
				floodedPersonsByAddress.add(floodedPersonByAddressDto);
				
				} catch (ParseException e) {
			
				e.printStackTrace();
			}
				 
		}
		return floodedPersonsByAddress;
	}
	
	private FloodedPersonByAddressDto constructFloodedPersonByAddressDto(Person person) throws ParseException {
		
		FloodedPersonByAddressDto floodedPersonByAddressDto = new FloodedPersonByAddressDto();
		floodedPersonByAddressDto.setAddress(person.getAddress());
		floodedPersonByAddressDto.setFirstName(person.getFirstName());
		floodedPersonByAddressDto.setLastName(person.getLastName());
		floodedPersonByAddressDto.setPhone(person.getPhone());
		floodedPersonByAddressDto.setAge(calculatePersonAge(person.getMedicalRecord().getBirthDate()));
		floodedPersonByAddressDto.setMedications(person.getMedicalRecord().getMedications());
		floodedPersonByAddressDto.setAllergies(person.getMedicalRecord().getAllergies());
		
		return floodedPersonByAddressDto;
	}
	
	@Override
	public List<PersonInfoDto> getPersonsInfo(String firstName, String lastName) {
		
		List<PersonInfoDto> personsInfo = new ArrayList<>();
		
		try {
			for (Person person : personDao.getListPersonsByLastName(lastName)) {
			
				for (MedicalRecord medicalRecord : medicalRecordDao.getMedicalRecordsBasedOnLastName(lastName)) {
					if(person.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName()) 
							&& person.getLastName().equalsIgnoreCase(medicalRecord.getLastName())) {
				
					person.setMedicalRecord(medicalRecordDao
							.getMedicalRecordBasedOnFirstAndLastName(person.getFirstName(), person.getLastName()));
					
					PersonInfoDto personInfoDto = constructPersonInfoDto(person);
					personsInfo.add(personInfoDto);
				}
			}
		}
			
		} catch (ParseException e) {
			
		}
		
		return personsInfo;
	}
	
	private PersonInfoDto constructPersonInfoDto(Person person) throws ParseException {
		
		PersonInfoDto personInfoDto = new PersonInfoDto(person.getFirstName(), person.getLastName(),person.getAddress());
		personInfoDto.setAge(calculatePersonAge(person.getMedicalRecord().getBirthDate()));
		personInfoDto.setEmail(person.getEmail());
		personInfoDto.setMedications(person.getMedicalRecord().getMedications());
		personInfoDto.setAllergies(person.getMedicalRecord().getAllergies());
		
		return personInfoDto;
	}

	@Override
	public List<String> getPersonsCommunityEmailByCity(String city) {
		
		List<String> emails = new ArrayList<>();
		List<Person> personEmailByCity = personDao.getListPersonsByCity(city);
		
		for (Person person : personEmailByCity) {
			String email = person.getEmail();
			
			emails.add(email);
		}
		
		return emails.stream().distinct().collect(Collectors.toList());
	}	
}
