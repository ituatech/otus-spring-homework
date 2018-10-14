package edu.otus.hw14.shell;

import edu.otus.hw14.entity.jpa.Address;
import edu.otus.hw14.entity.jpa.Person;
import edu.otus.hw14.repository.jpa.PersonRepository;
import edu.otus.hw14.repository.mongo.PersonMongoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * Created by Nik Bespalov on 03/10/2018.
 *
 * @author Nik Bespalov.
 */

@Log4j2
@ShellComponent
public class MainShell {
    private JobLauncher jobLauncher;
    private Job importUserJob;
    private PersonMongoRepository personMongoRepository;
    private PersonRepository personRepository;

    @Autowired
    public MainShell(JobLauncher jobLauncher,
                     Job importUserJob,
                     PersonMongoRepository personMongoRepository,
                     PersonRepository personRepository) {
        this.jobLauncher = jobLauncher;
        this.importUserJob = importUserJob;
        this.personMongoRepository = personMongoRepository;
        this.personRepository = personRepository;
    }

    @ShellMethod("start sync")
    public void start() throws JobParametersInvalidException,
                            JobExecutionAlreadyRunningException,
                            JobRestartException,
                            JobInstanceAlreadyCompleteException {
        jobLauncher.run(importUserJob,
                        new JobParametersBuilder()
                                .addLong("uniqueness", System.nanoTime())
                                .toJobParameters());
    }

    @ShellMethod("show all persons in mongo")
    public void show() {
        personMongoRepository.findAll().forEach(System.out::println);
    }

    @ShellMethod("add new person to JPA")
    public void add(@ShellOption String name,
                    @ShellOption Integer age,
                    @ShellOption String city,
                    @ShellOption String street,
                    @ShellOption Integer building_num) {
        Person newPerson = personRepository.save(Person.builder()
                                            .name(name)
                                            .age(age)
                                            .address(Address.builder()
                                                    .city(city)
                                                    .street(street)
                                                    .buildingNumber(building_num)
                                                    .build())
                                            .build());
        log.info("NEW PERSON SAVED: " + newPerson);
    }
}
