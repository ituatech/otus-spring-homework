package edu.otus.hw05.service;

import edu.otus.hw05.entities.Book;

/**
 * Created by Nik Bespalov on 19/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface BookService extends GeneralService<Book> {
    Book getByTitle(String title);
}
