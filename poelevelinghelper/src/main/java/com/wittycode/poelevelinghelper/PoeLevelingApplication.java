package com.wittycode.poelevelinghelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PoeLevelingApplication implements CommandLineRunner {

	@Autowired
	private GemImporter gemImporter;
	
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PoeLevelingApplication.class);
		app.run(args);
	}


	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		gemImporter.importGemInfo();
	}

}
