package edu.otus.hw04.mock;


import edu.otus.hw04.dao.QuestionsDAO;
import edu.otus.hw04.service.TestingServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
@Service("testBean")
public class TestingServiceMock extends TestingServiceImpl {

    public TestingServiceMock(QuestionsDAO questionsDAO, MessageSource messageSource) {
        super(questionsDAO, messageSource);
    }

    private List<String> answers;
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
