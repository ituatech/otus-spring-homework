package edu.otus.hw15.bootstrap;


import edu.otus.hw15.entity.Address;
import edu.otus.hw15.entity.Person;
import edu.otus.hw15.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nik Bespalov on 15/08/2018.
 *
 * @author Nik Bespalov.
 */
@Slf4j
@Component
public class BooksBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private PersonRepository personRepository;

    @Autowired
    public BooksBootStrap(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        personRepository.saveAll(init());
    }

    private List<Person> init() {
        List<Person> personList = new ArrayList<>();

        personList.add(Person.builder()
                .id("1")
                .name("Person 1")
                .age(20)
                .address(Address.builder().city("City1").street("Str1").buildingNumber(1).build())
                .build()
        );

        personList.add(Person.builder()
                .id("2")
                .name("Person 2")
                .age(20)
                .address(Address.builder().city("City2").street("Str2").buildingNumber(2).build())
                .build()
        );

        return personList;
    }
}
