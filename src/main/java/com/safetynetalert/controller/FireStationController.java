package com.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetalert.model.FireStation;
import com.safetynetalert.service.IFireStationService;

@RestController
public class FireStationController {
	
	@Autowired
	private IFireStationService fireStationService;
	
	@GetMapping("fireStation")
	public List<FireStation> list() {
		System.out.println("list");
		List<FireStation> lFireStation = this.fireStationService.list();
		System.out.println(lFireStation.size());
		return lFireStation;
	}
	
	@PostMapping("fireStation")
	public boolean insert(@RequestBody FireStation fireStation) {
		return this.fireStationService.insert(fireStation);
	}
	
	@PutMapping("fireStation")
	public boolean update(final FireStation fireStation) {
		return this.fireStationService.update(fireStation);
	}
	
	@DeleteMapping("fireStation")
	public boolean delete(final String lastname, final String firstname) {
		return this.fireStationService.delete(lastname, firstname);
	}

}
