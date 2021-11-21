package com.safetynetalert.dao;

import java.util.List;

import com.safetynetalert.model.Person;

public interface IPersonDAO {

	public List<Person> getPersonList();
	
	public boolean insert(final Person person);
	
	public boolean update(final Person person);
	
	public boolean delete(final String firstName, final String lastName);

	public List<Person> getListPersonsByAddress(final String address);

	List<Person> getListPersonsByLastName(final String lastName);

	List<Person> getListPersonsByCity(String city);

	List<Person> getListPersonEmails(String email);

	//List<String> getEmailsByCity(String city);
	
}
