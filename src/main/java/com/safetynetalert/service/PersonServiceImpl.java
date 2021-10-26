package com.safetynetalert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.dao.IPersonDAO;
import com.safetynetalert.model.Person;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonDAO personDAO;
	
	@Override
	public List<Person> list() {
		return this.personDAO.list();
	}

	@Override
	public boolean insert(Person person) {
		return this.personDAO.insert(person);
	}

	@Override
	public boolean update(Person person) {
		return this.personDAO.update(person);
	}

	@Override
	public boolean delete(String lastname, String firstname) {
		return this.personDAO.delete(lastname, firstname);
	}

}
