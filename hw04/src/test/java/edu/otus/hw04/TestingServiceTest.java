package edu.otus.hw04;

import edu.otus.hw04.dao.QuestionsDAO;
import edu.otus.hw04.dao.QuestionsDAOImpl;
import edu.otus.hw04.mock.TestingServiceMock;
import edu.otus.hw04.util.Properties;
import edu.otus.hw04.util.String2QuestionParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static org.mockito.Mockito.when;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingServiceTest {

    @Configuration
    static class Config {
        @Bean
        public Properties properties() {
            return new Properties() {{setPath("questions.csv");}};
        }
        @Bean
        public String2QuestionParser string2QuestionParser() {
            return new String2QuestionParser();
        }
        @Bean
        public QuestionsDAO questionsDAO() {
            return new QuestionsDAOImpl(string2QuestionParser(), properties());
        }

        @MockBean
        public static MessageSource messageSource;

        @Bean
        public TestingServiceMock testingServiceMock() {
            return new TestingServiceMock(questionsDAO(), messageSource);
        }
    }

    @Before
    public void init() {
        when(Config.messageSource.getMessage("testing.hacker", null, locale)).thenReturn(locale.toString().equals("ru_RU") ? "хакер" : "hacker");
        when(Config.messageSource.getMessage("testing.lamer", null, locale)).thenReturn(locale.toString().equals("ru_RU") ? "ламер" : "lamer");
        when(Config.messageSource.getMessage("testing.result", null, locale)).thenReturn("You are: ");
    }

    @Autowired
    private TestingServiceMock testingServiceMock;

    private static Locale locale = Locale.getDefault();

    @Test
    public void testWithGoodResults() {
        testingServiceMock.setAnswers(new ArrayList<>(Arrays.asList("2", "1,3", "2", "3", "1")));
        String expected = locale.toString().equals("ru_RU") ? "хакер" : "hacker";
        Assert.assertEquals(expected, testingServiceMock.start());
    }

    @Test
    public void testWithBadResults() {
        testingServiceMock.setAnswers(new ArrayList<>(Arrays.asList("1", "1", "1", "1", "1")));
        String expected = locale.toString().equals("ru_RU") ? "ламер" : "lamer";
        Assert.assertEquals(expected, testingServiceMock.start());
    }
}