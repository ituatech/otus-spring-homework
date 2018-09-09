package edu.otus.hw10.service.impl;

import edu.otus.hw10.entities.Book;
import edu.otus.hw10.entities.Comment;
import edu.otus.hw10.repository.BookRepository;
import edu.otus.hw10.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Book add(Book book) {
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
    public Book update(Book book) {
        book.setAuthors(book.getAuthors()
                .stream()
                .filter(a -> a.getAuthorName() != null && !a.getAuthorName().equals(""))
                .collect(Collectors.toList()));
        book.setGenres(book.getGenres()
                .stream()
                .filter(g -> g.getGenreName() != null && !g.getGenreName().equals(""))
                .collect(Collectors.toList()));
        book.setComments(getById(book.getId()).getComments());
        return bookRepository.save(book);
    }

    @Override
    public void remove(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(() -> {
            log.warn("No book with title " + title + " found");
            return new NoSuchElementException("No book with title " + title + " found");
        });
    }

    @Override
    public Book getById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> {
            log.warn("No book with id " + id + " found");
            return new NoSuchElementException("No book with id " + id + " found");
        });
    }

    @Override
    public Book addComment(String bookId, Comment comment) {
        Book book = getById(bookId);
        comment.setCreatedAt(LocalDateTime.now());
        book.getComments().add(comment);
        return add(book);
    }
}
