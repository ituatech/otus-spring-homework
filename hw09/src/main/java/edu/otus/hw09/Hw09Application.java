package edu.otus.hw09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "edu.otus.hw09.repository")
public class Hw09Application {
	public static void main(String[] args) {
		SpringApplication.run(Hw09Application.class, args);
	}
}
