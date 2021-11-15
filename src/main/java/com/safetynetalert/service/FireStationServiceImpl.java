package com.safetynetalert.service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.dao.IFireStationDAO;
import com.safetynetalert.model.FireStation;

@Service
public class FireStationServiceImpl implements IFireStationService {
	
	static final Logger logger = LogManager.getLogger(FireStationServiceImpl.class);
	
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
