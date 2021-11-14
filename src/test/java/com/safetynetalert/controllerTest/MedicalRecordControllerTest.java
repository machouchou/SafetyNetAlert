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

import com.safetynetalert.model.MedicalRecord;
import com.safetynetalert.service.IMedicalRecordService;

@SpringBootTest
@AutoConfigureMockMvc
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

}
