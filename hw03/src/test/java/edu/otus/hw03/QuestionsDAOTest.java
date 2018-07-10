package edu.otus.hw03;

import edu.otus.hw03.dao.QuestionsDAO;
import edu.otus.hw03.entity.Question;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Autowired
    private QuestionsDAO dao;


    @Test
    public void getAllQuestions() {
        List<Question> questions = dao.getQuestions();
        Assert.assertThat(questions.size(), Is.is(5));
        questions.forEach(Assert::assertNotNull);
    }
}
