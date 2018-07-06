package edu.otus;

import edu.otus.service.TestingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Nik Bespalov on 27/06/2018.
 *
 * @author Nik Bespalov.
 */
@Configuration
@ComponentScan(basePackages = "edu.otus.config")
@PropertySource("classpath:app.properties")
public class Main {
    /*
    Правильные ответы:
    1 - 2
    2 - 1,3
    3 - 2
    4 - 3
    5 - 1
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestingService testingService = (TestingService)context.getBean("testingService");
        testingService.start();
    }
}
