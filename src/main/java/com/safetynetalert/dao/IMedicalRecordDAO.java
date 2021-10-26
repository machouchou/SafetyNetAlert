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
	
	public List<MedicalRecord> list();

	public boolean insert(final MedicalRecord medicalRecord);

	public boolean update(final MedicalRecord medicalRecord);

	public boolean delete(final String lastname, final String firstname);

}
