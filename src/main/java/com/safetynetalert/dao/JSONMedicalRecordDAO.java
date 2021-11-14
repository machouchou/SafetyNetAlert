/**
 * 
 */
package com.safetynetalert.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynetalert.config.AppConfig;
import com.safetynetalert.data.Database;
import com.safetynetalert.model.MedicalRecord;

/**
 * @author eayic
 *
 */
@Repository
public class JSONMedicalRecordDAO implements IMedicalRecordDAO{
	
	static final Logger logger = LogManager.getLogger(JSONMedicalRecordDAO.class);

	@Autowired
	private Database database;
	
	@Override
	public List<MedicalRecord> getMedicalRecordList() {
		return database.getlMedicalRecord();
	}
	
	@Override
	public MedicalRecord getMedicalRecordBasedOnFirstAndLastName(String firstName, String lastName) {
		
		List<MedicalRecord> lMR = database.getlMedicalRecord();
		return database.getlMedicalRecord()
				.stream()
				.filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName))
				.findAny()
				.orElse(null);
	}
	
	@Override
	public List<MedicalRecord> getMedicalRecordsBasedOnLastName(String lastName) {
		return database.getlMedicalRecord()
				.stream()
				.filter(medicalRecord -> medicalRecord.getLastName().equals(lastName))
				.collect(Collectors.toList());
	}

	@Override
	public boolean insert(final MedicalRecord medicalRecord) {
		return database.getlMedicalRecord().add(medicalRecord);
	}

	@Override
	public boolean update(final MedicalRecord medicalRecord) {
		
		List<MedicalRecord> listMedicalRecord = this.getMedicalRecordList(); 
		for (MedicalRecord existingMedicalRecord : listMedicalRecord) {
			if (existingMedicalRecord.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
					&& existingMedicalRecord.getLastName().equalsIgnoreCase(medicalRecord.getLastName())) {
				existingMedicalRecord.setBirthDate(medicalRecord.getBirthDate());
				List<String> listMedication = existingMedicalRecord.getMedications();
				listMedication.clear();
				listMedication.addAll(medicalRecord.getMedications());
				List<String> listAllergies = existingMedicalRecord.getAllergies();
				listAllergies.clear();
				listAllergies.addAll(medicalRecord.getAllergies());

				return true;
			}
			
		}
		return false;
	}

	@Override
	public boolean delete(String lastName, String firstName) {
		List<MedicalRecord> listMedicalRecord = this.getMedicalRecordList();
		for (MedicalRecord existingMedicalRecord : listMedicalRecord) {
			if (existingMedicalRecord.getFirstName().equalsIgnoreCase(firstName)
					&& existingMedicalRecord.getLastName().equalsIgnoreCase(lastName)) {
				listMedicalRecord.remove(existingMedicalRecord);
				return true;
			}
		}
		return false;
	}

}
