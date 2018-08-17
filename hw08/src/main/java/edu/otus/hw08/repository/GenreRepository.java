package edu.otus.hw08.repository;

import edu.otus.hw08.entities.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByGenreName(String genreName);
}
