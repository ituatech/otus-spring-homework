package edu.otus.hw05.dao;

import edu.otus.hw05.entities.Author;

import java.util.List;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface AuthorDao extends GeneralDao<Author> {
    List<Author> getByBookId(int bookId);
    Author getByName(String name);
}
