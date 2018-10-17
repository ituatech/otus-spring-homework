package edu.otus.hw15.repository;

import edu.otus.hw15.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Nik Bespalov on 15/10/2018.
 *
 * @author Nik Bespalov.
 */
public interface PersonRepository extends MongoRepository<Person, String> {
}
