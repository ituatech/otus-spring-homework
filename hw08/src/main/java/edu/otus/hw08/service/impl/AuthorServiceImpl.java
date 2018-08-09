package edu.otus.hw08.service.impl;

import edu.otus.hw08.entities.Author;
import edu.otus.hw08.repository.AuthorRepository;
import edu.otus.hw08.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
