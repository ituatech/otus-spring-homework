package edu.otus.dao;

import edu.otus.entity.Question;

import java.util.List;

/**
 * Created by Nik Bespalov on 27/06/2018.
 *
 * @author Nik Bespalov.
 */
public interface QuestionsDAO {
    /**
     * Получение всех вопросов из файла
     * @return - список вопросов
     */
    List<Question> getQuestions();
}
