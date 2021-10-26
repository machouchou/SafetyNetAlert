package com.safetynetalert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.dao.IFireStationDAO;
import com.safetynetalert.model.FireStation;

@Service
public class FireStationServiceImpl implements IFireStationService {
	
	@Autowired
	private IFireStationDAO fireStationDAO;
	
	@Override
	public List<FireStation> list() {
		return this.fireStationDAO.list();
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
	public boolean delete(String address, String station) {
		return this.fireStationDAO.delete(address, station);
	}

}
