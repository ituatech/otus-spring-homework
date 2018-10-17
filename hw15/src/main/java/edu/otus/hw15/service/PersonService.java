package edu.otus.hw15.service;

import edu.otus.hw15.entity.Person;

import java.util.List;

/**
 * Created by Nik Bespalov on 15/10/2018.
 *
 * @author Nik Bespalov.
 */
public interface PersonService {
    List<Person> getAll();
    boolean add(Person person);
}
