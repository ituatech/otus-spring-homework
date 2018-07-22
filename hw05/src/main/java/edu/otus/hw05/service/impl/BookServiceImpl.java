package edu.otus.hw05.service.impl;

import edu.otus.hw05.dao.BookDao;
import edu.otus.hw05.entities.Book;
import edu.otus.hw05.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean add(Book book) {
        return bookDao.insert(book) > 0;
    }

    @Override
    public boolean remove(Book book) {
        return bookDao.remove(book) > 0;
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getByTitle(String title) {
        Book result = null;
        try{
            result = bookDao.getByTitle(title);
        }catch (EmptyResultDataAccessException e) {
            logger.warn("No book with title " + title + " found");
        }
        return result;
    }
}
