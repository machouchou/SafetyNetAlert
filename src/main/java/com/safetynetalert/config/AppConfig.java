package com.safetynetalert.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.safetynetalert.data.Database;

@Configuration
public class AppConfig {

	@Value("${app.data}")
	private String dataFileFromJson;
	
	static final Logger logger = LogManager.getLogger(AppConfig.class);
	
	@Bean
	public Database initDatabase() {
		logger.debug("initDatabase()");
		
		Database database = new Database();
		database.loadData(dataFileFromJson);
		
//		database.getlPerson().forEach(System.out::println);
//		database.getlFireStation().forEach(System.out::println);
//		database.getlMedicalRecord().forEach(System.out::println);
		
		return database;
	}
 }
