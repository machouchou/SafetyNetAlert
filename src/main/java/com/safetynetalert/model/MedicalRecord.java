 package com.safetynetalert.model;

import java.util.List;

public class MedicalRecord {
	private String firstName;
	private String lastName;
	private String birthDate;
	private List<String> medications;
	private List<String> allergies;
	
	public MedicalRecord() {
		super();
	}
	/**
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param medications
	 * @param allergies
	 */
	public MedicalRecord(String firstName, String lastName, String birthDate, List<String> medications,
			List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.medications = medications;
		this.allergies = allergies;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return the medications
	 */
	public List<String> getMedications() {
		return medications;
	}
	/**
	 * @param medications the medications to set
	 */
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	/**
	 * @return the allergies
	 */
	public List<String> getAllergies() {
		return allergies;
	}
	/**
	 * @param allergies the allergies to set
	 */
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	@Override
	public String toString() {
		return "MedicalRecord [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", medications=" + medications + ", allergies=" + allergies + "]";
	}
	
	
	

}
