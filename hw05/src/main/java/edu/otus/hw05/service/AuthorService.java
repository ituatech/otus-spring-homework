package edu.otus.hw05.service;

import edu.otus.hw05.entities.Author;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface AuthorService extends GeneralService<Author> {
    Author getByName(String name);
}
