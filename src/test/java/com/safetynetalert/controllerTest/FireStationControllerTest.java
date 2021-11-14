package com.safetynetalert.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.safetynetalert.model.FireStation;
import com.safetynetalert.service.IFireStationService;


@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	IFireStationService fireStationService;
	
	@BeforeEach
	public void config() {
		List<FireStation> lFireStations = new ArrayList<>();
		when(fireStationService.getFireStations()).thenReturn(lFireStations);
	}
	
	@Test
	void getFireStation() {
		try {
			this.mvc.perform(MockMvcRequestBuilders
					.get("/fireStation")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
