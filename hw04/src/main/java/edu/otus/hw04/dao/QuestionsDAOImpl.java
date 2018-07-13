package edu.otus.hw04.dao;

import edu.otus.hw04.entity.Question;
import edu.otus.hw04.util.Properties;
import edu.otus.hw04.util.String2QuestionParser;
import org.springframework.stereotype.Service;

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
@Service
public class QuestionsDAOImpl implements QuestionsDAO {

    private final String2QuestionParser parser;
    private final Properties properties;

    public QuestionsDAOImpl(String2QuestionParser parser, Properties properties) {
        this.parser = parser;
        this.properties = properties;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = null;
        try {
            questions = Files.readAllLines(Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(properties.getPath())).toURI()))
                    .stream().map(x -> parser.parse(new ArrayList<>(Arrays.asList(x.split(","))))).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
