package edu.otus;

import edu.otus.service.TestingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Nik Bespalov on 27/06/2018.
 *
 * @author Nik Bespalov.
 */
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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestingService testingService = (TestingService)context.getBean("testingService");
        testingService.start();
    }
}
