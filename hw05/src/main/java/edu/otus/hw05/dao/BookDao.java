package edu.otus.hw05.dao;

import edu.otus.hw05.entities.Book;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface BookDao extends GeneralDao<Book> {
    Book getByTitle(String title);
}
