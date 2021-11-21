package com.safetynetalert.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.dao.IMedicalRecordDAO;
import com.safetynetalert.model.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService{
	
	static final Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);
	
	@Autowired
	private IMedicalRecordDAO medicalRecordDAO;
	
	@Override
	public List<MedicalRecord> list() {
		return this.medicalRecordDAO.getMedicalRecordList();
	}

	@Override
	public boolean insert(MedicalRecord medicalRecord) {
		return this.medicalRecordDAO.insert(medicalRecord);
	}

	@Override
	public boolean update(MedicalRecord medicalRecord) {
		return this.medicalRecordDAO.update(medicalRecord);
	}

	@Override
	public boolean delete(String firstName, String lastName) {
		return this.medicalRecordDAO.delete(firstName, lastName);
	}

}
