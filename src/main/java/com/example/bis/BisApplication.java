package com.example.bis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.bis.simulator.repository")
public class BisApplication {
	public static void main(String[] args) {
		SpringApplication.run(BisApplication.class, args);
	}
}
