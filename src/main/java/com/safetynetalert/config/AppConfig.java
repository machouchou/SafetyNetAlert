package com.safetynetalert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.safetynetalert.data.Database;

@Configuration
public class AppConfig {

	@Bean
	public Database initDatabase() {
		Database database = new Database();
		database.loadData();
		return database;
	}
	
 }
