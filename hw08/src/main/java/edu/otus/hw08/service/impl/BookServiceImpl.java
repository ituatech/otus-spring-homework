package edu.otus.hw08.service.impl;

import edu.otus.hw08.entities.Book;
import edu.otus.hw08.repository.BookRepository;
import edu.otus.hw08.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookDao) {
        this.bookRepository = bookDao;
    }

    @Override
    public void add(Book book) {

        bookRepository.save(book);
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
}
