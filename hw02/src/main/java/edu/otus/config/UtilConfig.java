package edu.otus.config;

import edu.otus.util.String2QuestionParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Nik Bespalov on 01/07/2018.
 *
 * @author Nik Bespalov.
 */
@Configuration
public class UtilConfig {
    @Bean
    public String2QuestionParser string2QuestionParser() {
        return new String2QuestionParser();
    }
}
