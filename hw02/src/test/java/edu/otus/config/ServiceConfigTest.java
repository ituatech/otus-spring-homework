package edu.otus.config;

import edu.otus.dao.QuestionsDAO;
import edu.otus.mock.TestingServiceMock;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nik Bespalov on 01/07/2018.
 *
 * @author Nik Bespalov.
 */
@Configuration
public class ServiceConfigTest {
    @Bean
    public TestingServiceMock testingServiceMockWithAllRightAnswers(QuestionsDAO questionsDAO, MessageSource messageSource) {
        return new TestingServiceMock(questionsDAO, messageSource) {{setAnswers(new ArrayList<>(Arrays.asList("2", "1,3", "2", "3", "1")));}};
    }

    @Bean
    public TestingServiceMock testingServiceMockWrongAnswers(QuestionsDAO questionsDAO, MessageSource messageSource) {
        return new TestingServiceMock(questionsDAO, messageSource) {{setAnswers(new ArrayList<>(Arrays.asList("1", "1", "1", "1", "1")));}};
    }
}
