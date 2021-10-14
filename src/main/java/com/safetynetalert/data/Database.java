package com.safetynetalert.data;

import java.util.List;

import com.safetynetalert.model.Person;

public class Database {

	private List<Person> lPerson;
	
	public Database() {
		this.lPerson = null;
	}
	
	public void loadData() {
		PersonLoader personLoader = new PersonLoader();
		this.lPerson = personLoader.load();
	}

	/**
	 * @return the lPerson
	 */
	public List<Person> getlPerson() {
		return lPerson;
	}

	/**
	 * @param lPerson the lPerson to set
	 */
	public void setlPerson(List<Person> lPerson) {
		this.lPerson = lPerson;
	}
	
}
