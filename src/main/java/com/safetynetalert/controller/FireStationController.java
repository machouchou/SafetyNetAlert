package com.safetynetalert.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	static final Logger logger = LogManager.getLogger(FireStationController.class);
	
	@Autowired
	private IFireStationService fireStationService;
	
	@GetMapping("/fireStation")
	public List<FireStation> list() {
		logger.info("GET /fireStation called");
		return this.fireStationService.getFireStations();

	}
	
	@PostMapping("/fireStation")
	public boolean insert(@NotNull @RequestBody FireStation fireStation) {
		logger.info("POST /fireStation called");
		return this.fireStationService.insert(fireStation);
	}
	
	@PutMapping("/fireStation")
	public boolean update(@NotNull @RequestBody final FireStation fireStation) {
		logger.info("PUT /fireStation called");
		return this.fireStationService.update(fireStation);
	}
	
	@DeleteMapping("/fireStation")
	public boolean delete(@NotNull @RequestBody final FireStation fireStation) {
		logger.info("DELETE /fireStation called");
		return this.fireStationService.delete(fireStation);
	}
}
