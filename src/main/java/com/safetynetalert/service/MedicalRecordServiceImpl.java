package com.safetynetalert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynetalert.dao.IMedicalRecordDAO;
import com.safetynetalert.model.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements IMedicalRecordService{
	
	@Autowired
	private IMedicalRecordDAO medicalRecordDAO;
	
	@Override
	public List<MedicalRecord> list() {
		return this.medicalRecordDAO.list();
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
	public boolean delete(String lastname, String firstname) {
		return this.medicalRecordDAO.delete(lastname, firstname);
	}

}
