package edu.otus.hw06.service.impl;

import edu.otus.hw06.entities.Book;
import edu.otus.hw06.repository.BookRepository;
import edu.otus.hw06.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nik Bespalov on 19/07/2018.
 *
 * @author Nik Bespalov.
 */
@Service
public class BookServiceImpl implements BookService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookDao) {
        this.bookRepository = bookDao;
    }

    @Override
    public void add(Book book) {

        bookRepository.insert(book);
    }

    @Override
    public void remove(Book book) {
        bookRepository.remove(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book getByTitle(String title) {
        Book result = bookRepository.getByTitle(title);
        if (result == null) {
            logger.warn("No book with title " + title + " found");
        }
        return result;
    }
}
