package edu.otus;

import edu.otus.service.TestingService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
public class TestingServiceTest {
    private AnnotationConfigApplicationContext context;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(Main.class);
    }

    @Test
    public void goodTest() {
        TestingService service = (TestingService)context.getBean("testingServiceMockWithAllRightAnswers");
        service.start();
    }

    @Test
    public void badTest() {
        TestingService service = (TestingService)context.getBean("testingServiceMockWrongAnswers");
        service.start();
    }
}
