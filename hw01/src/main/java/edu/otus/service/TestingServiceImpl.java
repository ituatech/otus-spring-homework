package edu.otus.service;

import edu.otus.dao.QuestionsDAO;
import edu.otus.entity.Answer;
import edu.otus.entity.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Nik Bespalov on 28/06/2018.
 *
 * @author Nik Bespalov.
 */
public class TestingServiceImpl implements TestingService {

    private QuestionsDAO questionsDAO;
    /**
     * Кол-во правильных ответов
     */
    private int score = 0;

    public TestingServiceImpl(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    @Override
    public void start() {
        List<Question> questions = questionsDAO.getQuestions();
        questions.forEach(question -> {
            System.out.println(question2StringFormat(question));
            if (checkAnswer(question, getUserAnswer())) {
                score++;
            }
        });

        System.out.println("You are: " + getTestResult(score));
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
        System.out.println("Enter number of correct answer(for example:1 or 1,2,3): ");
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
        String result = "Гуманитарий";
        if (score > 3) {
            result = "Технарь";
        }
        return result;
    }
}
