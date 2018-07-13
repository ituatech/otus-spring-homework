package edu.otus.hw04.util;

import edu.otus.hw04.entity.Answer;
import edu.otus.hw04.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Nik Bespalov on 27/06/2018.
 *
 * @author Nik Bespalov.
 */
@Service
public class String2QuestionParser {
    /**
     *  индекс для обрезания служебной инфы из строки с вопросом или ответом
     */
    private final static int INDEX = 3;
    private final AtomicInteger counter = new AtomicInteger();

    /**
     * (q) - вопрос.
     * (w) - не правильный ответ.
     * (r) - правильный ответ.
     * @param strings - список строк для парсинга.
     * @return - объект вопроса.
     */
    public Question parse(final List<String> strings) {
        final Question question = new Question();
        counter.set(1);
        strings.forEach(x -> {
            if (x.startsWith("(q)")){
                question.setQuestion(x.substring(INDEX));
            } else if (x.startsWith("(w)")){
                question.getAnswers().add(new Answer(counter.getAndIncrement(), x.substring(INDEX)));
            } else if (x.startsWith("(r)")) {
                Answer answer = new Answer(counter.getAndIncrement(), x.substring(INDEX));
                question.getAnswers().add(answer);
                question.getRightAnswers().add(answer);
            }
        });
        return question;
    }
}