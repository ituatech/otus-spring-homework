package edu.otus.hw06.service.impl;

import edu.otus.hw06.entities.Author;
import edu.otus.hw06.repository.AuthorRepository;
import edu.otus.hw06.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorDao) {
        this.authorRepository = authorDao;
    }

    @Override
    public void add(Author author) {
        authorRepository.insert(author);
    }

    @Override
    public void remove(Author author) {
        authorRepository.remove(author);
    }

    @Override
    public Author getByName(String name) {
        Author result = authorRepository.getByName(name);
        if (result == null) {
            logger.warn("No author with name " + name + " found");
        }
        return result;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }
}
