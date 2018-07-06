package edu.otus.config;

import edu.otus.dao.QuestionsDAO;
import edu.otus.service.TestingService;
import edu.otus.service.TestingServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by Nik Bespalov on 01/07/2018.
 *
 * @author Nik Bespalov.
 */
@Configuration
public class ServiceConfig {
    @Bean
    public TestingService testingService(QuestionsDAO questionsDAO, MessageSource messageSource) {
        return new TestingServiceImpl(questionsDAO, messageSource);
    }

    @Bean
    public MessageSource messageSource () {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
