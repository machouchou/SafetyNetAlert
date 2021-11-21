/**
 * 
 */
package com.safetynetalert.dao;

import java.util.List;

import com.safetynetalert.model.MedicalRecord;

/**
 * @author eayic
 *
 */
public interface IMedicalRecordDAO {
	
	public List<MedicalRecord> getMedicalRecordList();

	public boolean insert(final MedicalRecord medicalRecord);

	public boolean update(final MedicalRecord medicalRecord);

	public boolean delete(final String firstName, final String lastName);

	MedicalRecord getMedicalRecordBasedOnFirstAndLastName(String firstName, String lastName);

	List<MedicalRecord> getMedicalRecordsBasedOnLastName(String lastName);

}
