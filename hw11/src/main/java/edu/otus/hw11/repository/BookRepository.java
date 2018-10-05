package edu.otus.hw11.repository;

import edu.otus.hw11.entities.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    Mono<Book> findByTitle(String title);
}
