package com.safetynetalert.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynetalert.data.Database;
import com.safetynetalert.model.Person;

@Repository
public class JSONPersonDAO implements IPersonDAO {

	@Autowired
	private Database database;

	@Override
	public List<Person> list() {
		return database.getlPerson();
	}

	@Override
	public boolean insert(final Person person) {
		return database.getlPerson().add(person);
	}

	@Override
	public boolean update(final Person person) {
		// Etape 1 : Trouver la personne à modifier venant de la liste de database (nom
		// + prenom)
		List<Person> myList = this.list();
		for (Person existingPerson : myList) {
			if (existingPerson.getFirstName().equalsIgnoreCase(person.getFirstName())
					&& existingPerson.getLastName().equalsIgnoreCase(person.getLastName())) {
				// Etape 2 : Quand la personne est trouvée, modifier les valeur de la personne
				// de la liste avec les valeurs qui sont dans la personne en paramètre de la
				// méthode
				existingPerson.setFirstName(person.getFirstName());
				existingPerson.setLastName(person.getLastName());
				existingPerson.setAddress(person.getAddress());
				existingPerson.setCity(person.getCity());
				existingPerson.setZip(person.getZip());
				existingPerson.setPhone(person.getPhone());
				existingPerson.setEmail(person.getEmail()); 

				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(final String lastName, final String firstName) {
		// Etape 1 : Trouver la personne à supprimer venant de la liste de database (nom
		// + prenom)
		List<Person> myList = this.list();
		for (Person existingPerson : myList) {
			if (existingPerson.getFirstName().equalsIgnoreCase(firstName)
					&& existingPerson.getLastName().equalsIgnoreCase(lastName)) {
				// Etape 2 : Supprimer de la liste la personne trouvée.
				myList.remove(existingPerson);
				return true;
			}
		}
		
		// myList.removeIf(existingPerson -> 
		// existingPerson.getFirstName().equals(firstName) && existingPerson.getLastName().equals(lastName));
		
		return false;
	}

}