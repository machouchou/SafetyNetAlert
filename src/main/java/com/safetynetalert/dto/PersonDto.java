package com.safetynetalert.dto;

public class PersonDto {
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 */
	public PersonDto(String firstName, String lastName, String address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;		
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
	 * @return the address
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
		
	@Override
	public String toString() {
		return "PersonDto [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", phone="
				+ phone + "]";
	}

}
