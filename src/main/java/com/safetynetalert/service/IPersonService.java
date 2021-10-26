package com.safetynetalert.service;

import java.util.List;

import com.safetynetalert.model.Person;

public interface IPersonService {

	public List<Person> list();
	
	public boolean insert(final Person person);
	
	public boolean update(final Person person);
	
	public boolean delete(final String lastname, final String firstname);
}
