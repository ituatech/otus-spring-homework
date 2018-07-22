package edu.otus.hw06.repository;

import edu.otus.hw06.entities.Author;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface AuthorRepository extends GeneralRepository<Author> {
    Author getByName(String name);
}
