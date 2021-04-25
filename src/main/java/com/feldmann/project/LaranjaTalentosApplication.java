package com.feldmann.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LaranjaTalentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaranjaTalentosApplication.class, args);
	}

}
