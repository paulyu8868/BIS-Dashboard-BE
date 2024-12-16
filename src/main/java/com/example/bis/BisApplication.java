package com.example.bis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.example.bis.simulator.model")
@EnableJpaRepositories(basePackages = "com.example.bis.simulator.repository")
@EnableScheduling
public class BisApplication {
	public static void main(String[] args) {
		SpringApplication.run(BisApplication.class, args);
	}
}
