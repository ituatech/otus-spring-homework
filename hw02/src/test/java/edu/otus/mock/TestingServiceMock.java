package edu.otus.mock;

import edu.otus.dao.QuestionsDAO;
import edu.otus.service.TestingServiceImpl;
import org.springframework.context.MessageSource;

import java.util.List;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
public class TestingServiceMock extends TestingServiceImpl {

    public TestingServiceMock(QuestionsDAO questionsDAO, MessageSource messageSource) {
        super(questionsDAO, messageSource);
    }

    private List<String> answers;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    protected String getUserAnswer() {
        String result = "";
        if (answers.size() > 0) {
            result = answers.get(0);
            answers.remove(0);
        }
        return result;
    }

}
