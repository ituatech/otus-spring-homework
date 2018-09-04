package edu.otus.hw11.service;

import edu.otus.hw11.entities.Book;
import edu.otus.hw11.entities.Comment;
import reactor.core.publisher.Mono;

public interface BookService extends GeneralService<Book> {
    Mono<Book> getByTitle(String title);
    Mono<Book> addComment(String bookId, Comment comment);
}
