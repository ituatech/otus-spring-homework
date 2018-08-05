package edu.otus.hw07.service;

import edu.otus.hw07.entities.Author;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface AuthorService extends GeneralService<Author> {
    Author getByName(String name);
}
