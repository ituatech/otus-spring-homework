package edu.otus.hw14.config;

import edu.otus.hw14.entity.jpa.Person;
import edu.otus.hw14.entity.mongo.PersonMongo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Nik Bespalov on 03/10/2018.
 *
 * @author Nik Bespalov.
 */

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private EntityManagerFactory entityManagerFactory;
    private MongoTemplate mongoTemplate;

    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       EntityManagerFactory entityManagerFactory,
                       MongoTemplate mongoTemplate) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public JpaPagingItemReader<Person> reader() {
        JpaPagingItemReader<Person> personJpaPagingItemReader = new JpaPagingItemReader<>();
        personJpaPagingItemReader.setQueryString("SELECT p FROM Person p");
        personJpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
        personJpaPagingItemReader.setPageSize(5);
        return personJpaPagingItemReader;
    }

    @Bean
    public ItemProcessor processor() {
        return person -> PersonMongo.builder()
                                    .id(String.valueOf(((Person)person).getId()))
                                    .name(((Person)person).getName())
                                    .age(((Person)person).getAge())
                                    .address(((Person)person).getAddress())
                                    .build();
    }

    @Bean
    public MongoItemWriter<PersonMongo> writer() {
        MongoItemWriter<PersonMongo> mongoItemWriter = new MongoItemWriter<>();
        mongoItemWriter.setTemplate(mongoTemplate);
        return mongoItemWriter;
    }

    @Bean
    public Job importPersonsJob(Step step1) {
        return jobBuilderFactory.get("importPersonsJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}