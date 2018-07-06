package edu.otus.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik Bespalov on 27/06/2018.
 * Вопрос
 * @author Nik Bespalov.
 */
@Data
public class Question {
    private String question;
    private List<Answer> answers = new ArrayList<>();
    private List<Answer> rightAnswers = new ArrayList<>();
}
