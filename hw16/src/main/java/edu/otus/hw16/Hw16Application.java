package edu.otus.hw16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "edu.otus.hw16.repository")
public class Hw16Application {
	public static void main(String[] args) {
		SpringApplication.run(Hw16Application.class, args);
	}
}
