package com.cloud.iot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Vivek
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class IoTApplication {

	@Autowired
	private CloudantClient client;
	
	@Value("${cloudant.database.name}")
	private String databaseName;
	
	public static void main(String[] args) {
		SpringApplication.run(IoTApplication.class, args);
	}

	@Bean
	public ObjectMapper mapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public Database cloudantDatabase()
	{
		return client.database(databaseName, false);
	}
}
