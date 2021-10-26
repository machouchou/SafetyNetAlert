package com.safetynetalert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.service.IMedicalRecordService;
import com.safetynetalert.service.MedicalRecordServiceImpl;

@RestController
public class MedicalRecordController {

	@Autowired
	private IMedicalRecordService medicalRecordService;
	
	@GetMapping("medicalRecord")
	public List<MedicalRecord> list() {
		System.out.println("list");
		List<MedicalRecord> lMedicalRecord = this.medicalRecordService.list();
		System.out.println(lMedicalRecord.size());
		return lMedicalRecord;
	}
	
	@PostMapping("medicalRecord")
	public boolean insert(@RequestBody MedicalRecord medicalRecord) {
		return this.medicalRecordService.insert(medicalRecord);
	}
	
	@PutMapping("medicalRecord")
	public boolean update(final MedicalRecord medicalRecord) {
		return this.medicalRecordService.update(medicalRecord);
	}
	
	@DeleteMapping("medicalRecord")
	public boolean delete(final String lastname, final String firstname) {
		return this.medicalRecordService.delete(lastname, firstname);
	}

}
