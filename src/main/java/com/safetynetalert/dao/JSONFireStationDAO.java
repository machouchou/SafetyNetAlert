 /**
 * 
 */
package com.safetynetalert.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynetalert.data.Database;
import com.safetynetalert.model.FireStation;

/**
 * @author eayic
 *
 */
@Repository
public class JSONFireStationDAO implements IFireStationDAO {
	
	@Autowired
	private Database database;
	
	@Override
	public List<FireStation> getFireStations() {
		
		return database.getlFireStation();
	}
	
	@Override
	public List<FireStation> getFireStations(String stationNumber) {
		
		return database.getlFireStation().stream()
        .filter(fireStation -> fireStation.getStation().equals(stationNumber))
        // .map(x -> x.getAddress()).distinct()
        .collect(Collectors.toList());
	}

	@Override
	public List<FireStation> getFireStationAddress(String address) {
		return database.getlFireStation().stream()
		        .filter(fireStation -> fireStation.getAddress().equals(address))
		        .distinct()
		        .collect(Collectors.toList());
	}
	
	@Override
	public List<String> getAddressesCoveredByAStationNumber(String stationNumber) {
		return database.getlFireStation().stream()
		        .filter(fireStation -> fireStation.getStation().equals(stationNumber))
		        .map(FireStation::getAddress).distinct()
		        .collect(Collectors.toList());
	}
	
	@Override 
	public boolean insert(final FireStation fireStation) {
		for (FireStation existingFireStation : getFireStations()) {
			if (existingFireStation.getAddress().equalsIgnoreCase(fireStation.getAddress())
					&& existingFireStation.getStation().equalsIgnoreCase(fireStation.getStation())) {
				return false;
			}
		}
		
		return database.getlFireStation().add(fireStation);
	}
	
	@Override
	public boolean update(final FireStation fireStation) {
		
		List<FireStation> listFireStation = this.getFireStations();
		for (FireStation existingFireStation : listFireStation) {
			if (existingFireStation.getAddress().equalsIgnoreCase(fireStation.getAddress())
					&& !existingFireStation.getStation().equalsIgnoreCase(fireStation.getStation())) {
				existingFireStation.setStation(fireStation.getStation());
				
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(final FireStation fireStation) {
		// Auto-generated method stub
		List<FireStation> listFireStation = this.getFireStations();
		for (FireStation existingFireStation : listFireStation) {
			if ( existingFireStation.getAddress().equalsIgnoreCase(fireStation.getAddress())
					&& existingFireStation.getStation().equalsIgnoreCase(fireStation.getStation())) {
				listFireStation.remove(existingFireStation); // supprimer le mapping d'une caserne ou d'une adresse
				return true;
			}
		}
		
		return false;
	}

}
