package com.safetynetalert.data;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.safetynetalert.model.MedicalRecord;

public class MedicalRecordLoader {

	public List<MedicalRecord> loadDataFromJsonFile(String fileName) {

		List<MedicalRecord> lMedicalRecord = new ArrayList<>();
		
		// Lire le fichier JSON et convertir les lignes en objet MedicalRecord
		try {
			String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

			JSONObject obj = new JSONObject(text);
			JSONArray arrMedicalRecords = obj.getJSONArray("medicalrecords");

			for (int z = 0; z < arrMedicalRecords.length(); z++) {
				String firstName = arrMedicalRecords.getJSONObject(z).getString("firstName");
				String lastName = arrMedicalRecords.getJSONObject(z).getString("lastName");
				String birthDate = arrMedicalRecords.getJSONObject(z).getString("birthdate");
				
				JSONArray arrMedications = arrMedicalRecords.getJSONObject(z).getJSONArray("medications");
				JSONArray arrAllergies = arrMedicalRecords.getJSONObject(z).getJSONArray("allergies");
				
				List<String> lMedications = new ArrayList<>();
				List<String> lAllergies = new ArrayList<>();
				
				//List<String> medications = arr.getJSONObject(z).getList<String>("medications");
				arrMedications.forEach(medication -> lMedications.add(medication.toString()));
				
				for (Object allergy : arrAllergies) {
					lAllergies.add(allergy.toString());
				}
				//List<String> allergies = arr.getJSONObject(z).getList<String>("allergies");

				MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthDate, lMedications, lAllergies);
				lMedicalRecord.add(medicalRecord);
			}
		} catch (Exception ex) {
		}

		return lMedicalRecord;
		
	}

}
