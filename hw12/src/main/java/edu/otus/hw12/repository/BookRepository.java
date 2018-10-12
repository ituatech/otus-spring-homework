package edu.otus.hw12.repository;

import edu.otus.hw12.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByTitle(String title);
}
