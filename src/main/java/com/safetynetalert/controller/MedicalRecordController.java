package com.safetynetalert.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {
	
	static final Logger logger = LogManager.getLogger(MedicalRecordController.class);

	@Autowired
	private IMedicalRecordService medicalRecordService;
	
	@GetMapping("/medicalRecord")
	public List<MedicalRecord> list() {
		return this.medicalRecordService.list();
		
	}
	
	@PostMapping("/medicalRecord")
	public boolean insert(@RequestBody MedicalRecord medicalRecord) {
		return this.medicalRecordService.insert(medicalRecord);
	}
	
	@PutMapping("/medicalRecord")
	public boolean update(final MedicalRecord medicalRecord) {
		return this.medicalRecordService.update(medicalRecord);
	}
	
	@DeleteMapping("/medicalRecord")
	public boolean delete(@RequestParam final String firstName, @RequestParam final String lastName) {
		return this.medicalRecordService.delete(firstName, lastName);
	}

}
