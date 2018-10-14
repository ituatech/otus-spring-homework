package edu.otus.hw14.repository.jpa;

import edu.otus.hw14.entity.jpa.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nik Bespalov on 03/10/2018.
 *
 * @author Nik Bespalov.
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
