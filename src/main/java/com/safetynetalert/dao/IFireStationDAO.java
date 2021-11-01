/**
 * 
 */
package com.safetynetalert.dao;

import java.util.List;
import java.util.Set;

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

}
