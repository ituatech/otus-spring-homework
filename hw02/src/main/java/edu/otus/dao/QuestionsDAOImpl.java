package edu.otus.dao;

import edu.otus.Main;
import edu.otus.entity.Question;
import edu.otus.util.String2QuestionParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Nik Bespalov on 27/06/2018.
 *
 * @author Nik Bespalov.
 */
public class QuestionsDAOImpl implements QuestionsDAO {

    private final String2QuestionParser parser;
    private final String path;

    public QuestionsDAOImpl(String2QuestionParser parser, String path) {
        this.parser = parser;
        this.path = path;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = null;
        try {
            questions = Files.readAllLines(Paths.get(Objects.requireNonNull(Main.class.getClassLoader().getResource(path)).toURI()))
                    .stream().map(x -> parser.parse(new ArrayList<>(Arrays.asList(x.split(","))))).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
