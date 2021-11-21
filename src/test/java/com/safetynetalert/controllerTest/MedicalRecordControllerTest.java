package com.safetynetalert.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.service.IMedicalRecordService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class MedicalRecordControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	IMedicalRecordService medicalRecordService;
	
	@BeforeEach
	public void config() {
		List<MedicalRecord> lMedicalRecord = new ArrayList<>();
		when(medicalRecordService.list()).thenReturn(lMedicalRecord);
	}
	
	@Test
	@Tag("GetMedicalRecordList")
	void getMedicalRecord() {
		try {
			this.mvc.perform(MockMvcRequestBuilders
					.get("/medicalRecord")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Tag("InsertMedicalRecord")
	void insert_ValidMedicalRecord_InsertionSucceded() throws Exception {
		try {
			//Act
			this.mvc.perform(MockMvcRequestBuilders
					.post("/medicalRecord")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{"
							+ "\"firstName\": \"Michel\",\r\n"
							+ "\"lastName\": \"Courvoisier\",\r\n"
							+ "\"birthdate\": \"08/18/2000\"\r\n"
							+ "}"))
					.andExpect(status().isOk());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Tag("UpdateMedicalRecord")
	void update_ValidMedicalRecord_UpdateSucceded() throws Exception {
		try {
			//Act
			this.mvc.perform(MockMvcRequestBuilders
					.put("/medicalRecord")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{"
							+ "\"firstName\": \"Jacob\",\r\n"
							+ "\"lastName\": \"Boyd\",\r\n"
							+ "\"birthdate\": \"05/06/1989\"\r\n"
							+ "}"))
					.andExpect(status().isOk());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Tag("DeleteMedicalRecord")
	void delete_ValidMedicalRecord_DeletionSucceded() throws Exception {
		try {
			//Act
			this.mvc.perform(MockMvcRequestBuilders
					.delete("medicalRecord")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{"
							+ "\"firstName\": \"Jonathan\",\r\n"
							+ "\"lastName\": \"Marrack\"\r\n"
							+ "}"))
					.andExpect(status().isOk());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
