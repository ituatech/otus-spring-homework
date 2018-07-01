package edu.otus.config;

import edu.otus.dao.QuestionsDAO;
import edu.otus.dao.QuestionsDAOImpl;
import edu.otus.util.String2QuestionParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Nik Bespalov on 01/07/2018.
 *
 * @author Nik Bespalov.
 */
@Configuration
public class DAOConfig {
    @Bean
    public QuestionsDAO questionsDAO(String2QuestionParser parser, @Value("${csv.path}") String path) {
        return new QuestionsDAOImpl(parser, path);
    }
}
