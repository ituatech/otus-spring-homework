package edu.otus.hw16.service;

import edu.otus.hw16.entities.Book;
import edu.otus.hw16.entities.Comment;

public interface BookService extends GeneralService<Book> {
    Book getByTitle(String title);
    Book addComment(String bookId, Comment comment);
}
