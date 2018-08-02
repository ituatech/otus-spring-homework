package edu.otus.hw07.repository;

import edu.otus.hw07.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByAuthorName(String authorName);
}
