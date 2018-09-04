package edu.otus.hw11.service.impl;

import edu.otus.hw11.entities.Book;
import edu.otus.hw11.entities.Comment;
import edu.otus.hw11.repository.BookRepository;
import edu.otus.hw11.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookDao) {
        this.bookRepository = bookDao;
    }

    @Override
    public Mono<Book> add(Book book) {
        book.setAuthors(book.getAuthors()
                .stream()
                .filter(a -> a.getAuthorName() != null && !a.getAuthorName().equals(""))
                .collect(Collectors.toList()));
        book.setGenres(book.getGenres()
                .stream()
                .filter(g -> g.getGenreName() != null && !g.getGenreName().equals(""))
                .collect(Collectors.toList()));
        return bookRepository.save(book);
    }

    @Override
    public Mono<Book> update(Book book) {
        book.setAuthors(book.getAuthors()
                .stream()
                .filter(a -> a.getAuthorName() != null && !a.getAuthorName().equals(""))
                .collect(Collectors.toList()));
        book.setGenres(book.getGenres()
                .stream()
                .filter(g -> g.getGenreName() != null && !g.getGenreName().equals(""))
                .collect(Collectors.toList()));
        book.setComments(getById(book.getId()).block().getComments());
        return bookRepository.save(book);
    }

    @Override
    public Mono<Void> remove(Book book) {
        return bookRepository.delete(book);
    }

    @Override
    public Flux<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> getByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Mono<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Mono<Book> addComment(String bookId, Comment comment) {
        Book book = getById(bookId).block();
        comment.setCreatedAt(LocalDateTime.now());
        book.getComments().add(comment);
        return add(book);
    }
}
