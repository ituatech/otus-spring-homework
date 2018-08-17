package edu.otus.hw08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "edu.otus.hw08")
public class Hw08Application {
	public static void main(String[] args) {
		SpringApplication.run(Hw08Application.class, args);
	}
}
