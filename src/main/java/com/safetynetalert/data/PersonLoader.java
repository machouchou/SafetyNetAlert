package com.safetynetalert.data;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.safetynetalert.model.Person;

public class PersonLoader {

	public List<Person> loadDataFromJsonFile(String fileName) {

		List<Person> lPerson = new ArrayList<>();

		// Lire le fichier JSON et convertir les lignes en objet Person
		try {
			String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

			JSONObject obj = new JSONObject(text);
			JSONArray arr = obj.getJSONArray("persons");

			for (int i = 0; i < arr.length(); i++) {
				String firstName = arr.getJSONObject(i).getString("firstName");
				String lastName = arr.getJSONObject(i).getString("lastName");
				String address = arr.getJSONObject(i).getString("address");
				String city = arr.getJSONObject(i).getString("city");
				String zip = arr.getJSONObject(i).getString("zip");
				String phone = arr.getJSONObject(i).getString("phone");
				String email = arr.getJSONObject(i).getString("email");

				Person person = new Person(firstName, lastName, address, city, zip, phone, email);
				lPerson.add(person);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return lPerson;
	}
}
