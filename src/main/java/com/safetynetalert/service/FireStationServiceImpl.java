package com.safetynetalert.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.dao.IFireStationDAO;
import com.safetynetalert.model.FireStation;

@Service
public class FireStationServiceImpl implements IFireStationService {
	
	@Autowired
	private IFireStationDAO fireStationDAO;
	
	@Override
	public List<FireStation> getFireStations() {
		return this.fireStationDAO.getFireStations();
	}

	@Override
	public boolean insert(FireStation fireStation) {
		return this.fireStationDAO.insert(fireStation);
	}

	@Override
	public boolean update(FireStation fireStation) {
		return this.fireStationDAO.update(fireStation);
	}

	@Override
	public boolean delete(final FireStation fireStation) {
		return this.fireStationDAO.delete(fireStation);
	}

}
