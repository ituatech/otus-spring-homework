package edu.otus.hw04;

import edu.otus.hw04.dao.QuestionsDAO;
import edu.otus.hw04.dao.QuestionsDAOImpl;
import edu.otus.hw04.entity.Question;
import edu.otus.hw04.util.Properties;
import edu.otus.hw04.util.String2QuestionParser;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionsDAOTest {

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
    }

    @Autowired
    private QuestionsDAO questionsDAO;

    @Test
    public void getAllQuestions() {
        List<Question> questions = questionsDAO.getQuestions();
        Assert.assertThat(questions.size(), Is.is(5));
        questions.forEach(Assert::assertNotNull);
    }
}
