package edu.otus.hw07.repository;

import edu.otus.hw07.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nik Bespalov on 22/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByBookTitle(String bookTitle);
}
