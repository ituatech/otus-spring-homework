package edu.otus.hw06.repository;


import edu.otus.hw06.entities.Book;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface BookRepository extends GeneralRepository<Book> {
    Book getByTitle(String title);
}
