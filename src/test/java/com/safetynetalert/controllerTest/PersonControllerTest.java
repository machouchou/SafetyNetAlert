package com.safetynetalert.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.safetynetalert.model.Person;
import com.safetynetalert.service.IPersonService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)

public class PersonControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webContext;
	
	@MockBean
	IPersonService personService;
	
	@BeforeEach
	public void config() {
		mvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	@Tag("RetrievePersonList")
	void getPersonLists() throws Exception {
		// Arrange 
		List<Person> lPerson = new ArrayList<>();
		Person person = new Person("Aicha", "Kelani", "45 avenue", "City", "zip", "phone", "mail");
		lPerson.add(person);
		when(personService.list()).thenReturn(lPerson);
		
		// Act & Assert
		try {
			//Act
			this.mvc.perform(MockMvcRequestBuilders
					.get("/person")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().string("[{\"firstName\":\"Aicha\",\"lastName\":\"Kelani\",\"address\":\"45 avenue\",\"city\":\"City\",\"zip\":\"zip\",\"phone\":\"phone\",\"email\":\"mail\"}]"));
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Tag("InsertPerson")
	void insert_ValidPerson_InsertionSucceded() throws Exception {
		try {
			//Act
			this.mvc.perform(MockMvcRequestBuilders
					.post("/person")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{"
							+ "\"firstName\": \"Michel\",\r\n"
							+ "\"lastName\": \"Courvoisier\",\r\n"
							+ "\"address\": \"1509 Culver St\",\r\n"
							+ "\"city\": \"Culver\",\r\n"
							+ "\"zip\": \"97451\",\r\n"
							+ "\"phone\": \"841-874-6512\",\r\n"
							+ "\"email\": \"jaboyd@email.com\"\r\n"
							+ "}"))
					.andExpect(status().isOk());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Tag("InsertPerson")
	void insert_InvalidPerson_InsertionFailed() {
		try {
			//Act
			this.mvc.perform(MockMvcRequestBuilders
					.post("/person")
					.contentType(MediaType.APPLICATION_JSON)
					.content(""))
					.andExpect(status().isBadRequest());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Tag("UpdatePerson")
	void update_ValidPerson_UpdateSucceded() throws Exception {
		try {
			//Act
			this.mvc.perform(MockMvcRequestBuilders
					.put("/person")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{"
							+ "\"firstName\": \"Warren\",\r\n"
							+ "\"lastName\": \"Zemick\",\r\n"
							+ "\"address\": \"1509 Culver St\",\r\n"
							+ "\"city\": \"Culver\",\r\n"
							+ "\"zip\": \"97451\",\r\n"
							+ "\"phone\": \"841-874-7512\",\r\n"
							+ "\"email\": \"jaboyd@email.com\"\r\n"
							+ "}"))
					.andExpect(status().isOk());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Tag("DeletePerson")
	void delete_ValidPerson_PersonIsDeleted() throws Exception {
		try {
			insert_ValidPerson_InsertionSucceded();
			//Act
			this.mvc.perform(MockMvcRequestBuilders
					.delete("person")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"firstName\": \"Michel\",\"lastName\": \"Courvoisier\"}")
					.accept(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(status().isOk());			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
}
