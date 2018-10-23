package edu.otus.hw15.service.Impl;

import edu.otus.hw15.entity.Person;
import edu.otus.hw15.repository.PersonRepository;
import edu.otus.hw15.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nik Bespalov on 15/10/2018.
 *
 * @author Nik Bespalov.
 */
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public boolean add(Person person) {
        Person savedPerson = null;
        try {
            savedPerson = personRepository.save(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedPerson != null;
    }
}
