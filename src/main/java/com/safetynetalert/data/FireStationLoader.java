package com.safetynetalert.data;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.safetynetalert.model.FireStation;

public class FireStationLoader {
	
	public List<FireStation> loadDataFromJsonFile(String fileName) {
		
		List<FireStation> lFireStation = new ArrayList<>();
		
		try {
			String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

			JSONObject obj = new JSONObject(text);
			JSONArray arr = obj.getJSONArray("firestations");

			for (int j = 0; j < arr.length(); j++) {
				
				String address = arr.getJSONObject(j).getString("address");
				String station = arr.getJSONObject(j).getString("station");

				FireStation fireStation = new FireStation(address, station);
				lFireStation.add(fireStation);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return lFireStation;
	}

}
