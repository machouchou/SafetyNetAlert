package com.safetynetalert.dto;

import java.util.List;
import java.util.Objects;

public class PersonLivingAtAddressDto {
	@Override
	public int hashCode() {
		return Objects.hash(age, allergies, firstName, lastName, medications, phone, station);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonLivingAtAddressDto other = (PersonLivingAtAddressDto) obj;
		return age == other.age && Objects.equals(allergies, other.allergies)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(medications, other.medications) && Objects.equals(phone, other.phone)
				&& Objects.equals(station, other.station);
	}

	private String firstName;
	private String lastName;
	private String phone;
	private String station;
	private int age;
	private List<String> medications;
	private List<String> allergies;
	
	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


}
