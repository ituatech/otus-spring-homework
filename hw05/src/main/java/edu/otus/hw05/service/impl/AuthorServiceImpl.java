package edu.otus.hw05.service.impl;

import edu.otus.hw05.dao.AuthorDao;
import edu.otus.hw05.entities.Author;
import edu.otus.hw05.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public boolean add(Author author) {
        return authorDao.insert(author) > 0;
    }

    @Override
    public boolean remove(Author author) {
        return authorDao.remove(author) > 0;
    }

    @Override
    public Author getByName(String name) {
        Author result = null;
        try{
            result = authorDao.getByName(name);
        }catch (EmptyResultDataAccessException e) {
            logger.warn("No author with name " + name + " found");
        }
        return result;
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
