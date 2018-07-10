package edu.otus.hw03;

import edu.otus.hw03.service.TestingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Hw03Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Hw03Application.class, args);
		TestingService service = context.getBean(TestingService.class);
		service.start();
	}
}
