package edu.otus.hw08.repository;

import edu.otus.hw08.entities.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByAuthorName(String authorName);
}
