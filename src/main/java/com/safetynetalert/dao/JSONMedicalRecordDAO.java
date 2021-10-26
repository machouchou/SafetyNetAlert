/**
 * 
 */
package com.safetynetalert.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynetalert.data.Database;
import com.safetynetalert.model.MedicalRecord;

/**
 * @author eayic
 *
 */
@Repository
public class JSONMedicalRecordDAO implements IMedicalRecordDAO{

	@Autowired
	private Database database;
	
	@Override
	public List<MedicalRecord> list() {
		// Auto-generated method stub
		return database.getlMedicalRecord();
	}

	@Override
	public boolean insert(MedicalRecord medicalRecord) {
		// Auto-generated method stub
		return database.getlMedicalRecord().add(medicalRecord);
	}

	@Override
	public boolean update(MedicalRecord medicalRecord) {
		// Auto-generated method stub
		
		List<MedicalRecord> myList; 
		return false;
	}

	@Override
	public boolean delete(String lastname, String firstname) {
		// Auto-generated method stub
		return false;
	}

}
