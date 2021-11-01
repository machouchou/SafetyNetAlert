package com.safetynetalert.controller;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetalert.model.FireStation;
import com.safetynetalert.model.Person;
import com.safetynetalert.service.IFireStationService;

@RestController
public class FireStationController {
	
	@Autowired
	private IFireStationService fireStationService;
	
	@GetMapping("fireStation")
	public List<FireStation> list() {
		return this.fireStationService.getFireStations();

	}
	
	@PostMapping("fireStation")
	public boolean insert(@NotNull @RequestBody FireStation fireStation) {
		return this.fireStationService.insert(fireStation);
	}
	
	@PutMapping("fireStation")
	public boolean update(@NotNull @RequestBody final FireStation fireStation) {
		return this.fireStationService.update(fireStation);
	}
	
	@DeleteMapping("fireStation")
	public boolean delete(@NotNull @RequestBody final FireStation fireStation) {
		return this.fireStationService.delete(fireStation);
	}
}
