package com.safetynetalert.data;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import com.safetynetalert.model.FireStation;
import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.model.Person;

public class Database {

	private List<Person> lPerson;
	private List<FireStation> lFireStation;
	private List<MedicalRecord> lMedicalRecord;

	public void loadData(String dataFile) {
		PersonLoader personLoader = new PersonLoader();
		this.lPerson = personLoader.loadDataFromJsonFile(dataFile);
		FireStationLoader fireStationLoader = new FireStationLoader();
		this.lFireStation = fireStationLoader.loadDataFromJsonFile(dataFile);
		MedicalRecordLoader medicalRecordLoader = new MedicalRecordLoader();
		this.lMedicalRecord = medicalRecordLoader.loadDataFromJsonFile(dataFile);
	}

	/**
	 * @return the lPerson
	 */
	public List<Person> getlPerson() {
		return lPerson;
	}

	
	/**
	 * @return the lFireStation
	 */
	public List<FireStation> getlFireStation() {
		return lFireStation;
	}

	
	/**
	 * @return the lMedicalRecord
	 */
	public List<MedicalRecord> getlMedicalRecord() {
		return lMedicalRecord;
	}


}
