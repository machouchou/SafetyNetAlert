/**
 * 
 */
package com.safetynetalert.service;

import java.util.List;

import com.safetynetalert.model.FireStation;

/**
 * @author eayic
 *
 */
public interface IFireStationService {

	public List<FireStation> getFireStations();

	public boolean insert(final FireStation fireStation);

	public boolean update(final FireStation fireStation);

	public boolean delete(final FireStation fireStation);

}
