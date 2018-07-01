package edu.otus;

import edu.otus.dao.QuestionsDAO;
import edu.otus.entity.Question;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
public class QuestionsDAOTest {
    private AnnotationConfigApplicationContext context;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(Main.class);
    }

    @Test
    public void test() {
        QuestionsDAO dao = context.getBean(QuestionsDAO.class);
        List<Question> questions = dao.getQuestions();
        Assert.assertThat(questions.size(), Is.is(5));
        questions.forEach(Assert::assertNotNull);
    }
}
