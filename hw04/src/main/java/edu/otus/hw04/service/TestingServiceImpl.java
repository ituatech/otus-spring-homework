package edu.otus.hw04.service;

import edu.otus.hw04.dao.QuestionsDAO;
import edu.otus.hw04.entity.Answer;
import edu.otus.hw04.entity.Question;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
@Service("service")
public class TestingServiceImpl implements TestingService {
    private final Locale locale = Locale.getDefault();
    private final MessageSource messageSource;
    private final QuestionsDAO questionsDAO;

    /**
     * Кол-во правильных ответов
     */
    private int score = 0;

    public TestingServiceImpl(QuestionsDAO questionsDAO, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.questionsDAO = questionsDAO;
    }

    @Override
    public String start() {
        List<Question> questions = questionsDAO.getQuestions();
        questions.forEach(question -> {
            System.out.println(question2StringFormat(question));
            if (checkAnswer(question, getUserAnswer())) {
                score++;
            }
        });
        String result = getTestResult(score);
        System.out.println(messageSource.getMessage("testing.result", null, locale) + result);
        return result;
    }

    /**
     * Переводит вопрос в строчный формат
     * @param q - поврос
     * @return - строка с вотросом
     */
    private String question2StringFormat(Question q) {
        StringBuilder output = new StringBuilder(q.getQuestion() + "\n");
        for(Answer answer : q.getAnswers()) {
            output.append(answer2StringFormat(answer));
        }
        return output.toString();
    }

    /**
     * Переводит ответ к вопросу в строчный формат
     * @param a - ответ
     * @return - строка с ответом
     */
    private String answer2StringFormat(Answer a) {
        return a.getId() + ". " + a.getAnswer() + "\n";
    }

    /**
     * Получение ответа пользователя с консоли
     * @return - ответ пользователя
     */
    protected String getUserAnswer() {
        Scanner in = new Scanner(System.in);
        System.out.println(messageSource.getMessage("testing.question", null, locale));
        return in.nextLine();
    }

    /**
     * Проверка ответа пользователя на вопрос
     * @param q - вопрос
     * @param answer - ответ пользователя
     * @return - результат, правильно или нет
     */
    private boolean checkAnswer(Question q, String answer) {
        List<String> answers = Arrays.asList(answer.split(","));
        boolean result = false;
        List<String> rightIds = q.getRightAnswers()
                .stream()
                .map(answer1 -> Integer.toString(answer1.getId()))
                .collect(Collectors.toList());
        if (q.getRightAnswers().size() == answers.size() && rightIds.containsAll(answers)) {
            result = true;
        }
        return result;
    }

    /**
     * Итог тестирования
     * @param score - кол-во правильных ответов
     * @return - диагноз
     */
    private String getTestResult(int score) {
        String result = messageSource.getMessage("testing.lamer", null, locale);
        if (score > 3) {
            result = messageSource.getMessage("testing.hacker", null, locale);
        }
        return result;
    }
}
