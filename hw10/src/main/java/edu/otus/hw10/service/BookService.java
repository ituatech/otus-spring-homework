package edu.otus.hw10.service;

import edu.otus.hw10.entities.Book;
import edu.otus.hw10.entities.Comment;

public interface BookService extends GeneralService<Book> {
    Book getByTitle(String title);
    Book addComment(String bookId, Comment comment);
}
