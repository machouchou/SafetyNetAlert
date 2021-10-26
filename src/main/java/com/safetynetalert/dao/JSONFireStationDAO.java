 /**
 * 
 */
package com.safetynetalert.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynetalert.data.Database;
import com.safetynetalert.model.FireStation;
import com.safetynetalert.model.MedicalRecord;

/**
 * @author eayic
 *
 */
@Repository
public class JSONFireStationDAO implements IFireStationDAO {
	
	@Autowired
	private Database database;
	
	@Override
	public List<FireStation> list() {
		return database.getlFireStation();
	}

	@Override
	public boolean insert(final FireStation fireStation) {
		return database.getlFireStation().add(fireStation);
	}
	
	@Override
	public boolean update(FireStation fireStation) {
		// Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String lastname, String firstname) {
		// Auto-generated method stub
		return false;
	}
	

}
