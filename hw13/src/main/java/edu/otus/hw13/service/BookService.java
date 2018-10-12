package edu.otus.hw13.service;

import edu.otus.hw13.entities.Book;
import edu.otus.hw13.entities.Comment;

public interface BookService extends GeneralService<Book> {
    Book getByTitle(String title);
    Book addComment(String bookId, Comment comment);
}
