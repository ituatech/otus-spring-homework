package edu.otus;

import edu.otus.dao.QuestionsDAO;
import edu.otus.entity.Question;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
public class QuestionsDAOTest {
    ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("/spring-context-test.xml");
    }

    @Test
    public void test() {
        QuestionsDAO dao = context.getBean(QuestionsDAO.class);
        List<Question> questions = dao.getQuestions();
        Assert.assertThat(questions.size(), Is.is(5));
        questions.forEach(Assert::assertNotNull);
    }
}
