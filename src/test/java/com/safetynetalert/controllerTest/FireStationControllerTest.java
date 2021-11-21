package com.safetynetalert.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.safetynetalert.model.FireStation;
import com.safetynetalert.service.IFireStationService;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)

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
	@Tag("RetrieveFireStationList")
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
	
	@Test
	@Tag("InsertFireStation")
	void insert_Valid_FireStation_InsertionSucceded() {
		try {
			// Act
			this.mvc.perform(MockMvcRequestBuilders
					.post("/fireStation")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{"
							+ "\"address\": \"17 rue Bremontier\",\r\n"
							+ "\"station\": \"5\"\r\n"
							+ "}"))
					.andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Tag("UpdateFireStation")
	void update_Valid_FireStation_UpdateSucceded() {
		try {
			// Act
			this.mvc.perform(MockMvcRequestBuilders
					.put("/fireStation")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{"
							+ "\"address\": \"947 E. Rose Dr\",\r\n"
							+ "\"station\": \"5\"\r\n"
							+ "}"))
					.andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Tag("DeleteFireStation")
	void delete_Valid_FireStation_DeletionSucceded() {
		try {
			// Act
			this.mvc.perform(MockMvcRequestBuilders
					.delete("/fireStation")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{"
							+ "\"address\": \"489 Manchester St\",\r\n"
							+ "\"station\": \"4\"\r\n"
							+ "}"))
					.andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
