package edu.otus.hw10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "edu.otus.hw10.repository")
public class Hw10Application {
	public static void main(String[] args) {
		SpringApplication.run(Hw10Application.class, args);
	}
}