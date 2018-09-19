package edu.otus.hw12.service;

import edu.otus.hw12.entities.Book;
import edu.otus.hw12.entities.Comment;

public interface BookService extends GeneralService<Book> {
    Book getByTitle(String title);
    Book addComment(String bookId, Comment comment);
}
