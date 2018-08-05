package edu.otus.hw07.service.impl;

import edu.otus.hw07.entities.Author;
import edu.otus.hw07.repository.AuthorRepository;
import edu.otus.hw07.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorDao) {
        this.authorRepository = authorDao;
    }

    @Override
    public void add(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void remove(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public Author getByName(String name) {
        return authorRepository.findByAuthorName(name).orElseThrow(() -> {
            log.warn("No author with name " + name + " found");
            return new NoSuchElementException("No author with name " + name + " found");
        });
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
