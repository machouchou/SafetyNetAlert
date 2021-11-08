package com.safetynetalert.dao;

import java.util.List;

import com.safetynetalert.model.Person;

public interface IPersonDAO {

	public List<Person> getPersonList();
	
	public boolean insert(final Person person);
	
	public boolean update(final Person person);
	
	public boolean delete(final String lastname, final String firstname);

	public List<Person> getListPersonsByAddress(final String address);

	List<Person> getListPersonsByLastName(final String lastName);
	
	
}
