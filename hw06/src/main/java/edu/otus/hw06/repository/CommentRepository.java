package edu.otus.hw06.repository;

import edu.otus.hw06.entities.Comment;

import java.util.List;

/**
 * Created by Nik Bespalov on 22/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface CommentRepository {
    void insert(Comment comment);
    void remove(Comment comment);
    List<Comment> getByBook(String bookTitle);
}
