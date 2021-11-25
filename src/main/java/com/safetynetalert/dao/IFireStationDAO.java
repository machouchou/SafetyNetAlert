/**
 * 
 */
package com.safetynetalert.dao;

import java.util.List;

import com.safetynetalert.model.FireStation;

/**
 * @author eayic
 *
 */
public interface IFireStationDAO {

	public List<FireStation> getFireStations();

	public boolean insert(final FireStation fireStation);

	public boolean update(final FireStation fireStation);

	public boolean delete(final FireStation fireStation);
	
	public List<FireStation> getFireStations(String stationNumber);
	
	public List<FireStation> getFireStationAddress(String address);

	List<String> getAddressesCoveredByAStationNumber(String stationNumber);

}
 