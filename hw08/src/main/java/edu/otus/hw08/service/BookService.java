package edu.otus.hw08.service;

import edu.otus.hw08.entities.Book;

public interface BookService extends GeneralService<Book> {
    Book getByTitle(String title);
}
