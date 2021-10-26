package com.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetalert.model.Person;
import com.safetynetalert.service.IPersonService;

@RestController
public class PersonController {

	@Autowired
	private IPersonService personService;
	
	@GetMapping("person")
	public List<Person> list() {
		System.out.println("list");
		List<Person> lPer = this.personService.list();
		System.out.println(lPer.size());
		return lPer;
	}
	
	@PostMapping("person")
	public boolean insert(@RequestBody Person person) {
		return this.personService.insert(person);
	}
	
	@PutMapping("person")
	public boolean update(final Person person) {
		return this.personService.update(person);
	}
	
	@DeleteMapping("person")
	public boolean delete(final String lastname, final String firstname) {
		return this.personService.delete(lastname, firstname);
	}
}
