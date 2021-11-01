package com.safetynetalert.model;

import java.util.Objects;

public class FireStation {
	private String address;
	private String station;
	/**
	 * @param address
	 * @param station
	 */
	public FireStation(String address, String station) {
		super();
		this.address = address;
		this.station = station;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return station
	 */
	public String getStation() {
		return station;
	}
	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}
	@Override
	public String toString() {
		return "FireStation [address=" + address + ", station=" + station + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, station);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		FireStation other = (FireStation) obj;
		return Objects.equals(address, other.address) && Objects.equals(station, other.station);
	}
}
