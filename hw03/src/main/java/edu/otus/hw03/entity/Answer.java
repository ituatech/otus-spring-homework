package edu.otus.hw03.entity;

import lombok.Data;

/**
 * Created by Nik Bespalov on 28/06/2018.
 * Вариант ответа на вопрос
 * @author Nik Bespalov.
 */
@Data
public class Answer {
    private int id;
    private String answer;

    public Answer(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }
}
