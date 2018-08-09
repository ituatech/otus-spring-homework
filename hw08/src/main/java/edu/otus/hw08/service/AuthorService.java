package edu.otus.hw08.service;

import edu.otus.hw08.entities.Author;

public interface AuthorService extends GeneralService<Author> {
    Author getByName(String name);
}
