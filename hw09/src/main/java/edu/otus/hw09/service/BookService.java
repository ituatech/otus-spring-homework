package edu.otus.hw09.service;

import edu.otus.hw09.entities.Book;
import edu.otus.hw09.entities.Comment;

public interface BookService extends GeneralService<Book> {
    Book getByTitle(String title);
    Book addComment(String bookId, Comment comment);
}
