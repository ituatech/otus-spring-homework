package edu.otus.hw08.repository;

import edu.otus.hw08.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByTitle(String title);
}
