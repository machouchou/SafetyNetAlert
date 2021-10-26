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

	public List<FireStation> list();

	public boolean insert(final FireStation fireStation);

	public boolean update(final FireStation fireStation);

	public boolean delete(final String address, final String station);

}
