package edu.otus.hw06.service;

import edu.otus.hw06.entities.Comment;

import java.util.List;

/**
 * Created by Nik Bespalov on 22/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface CommentService {
    void add(Comment comment);
    void remove(Comment comment);
    List<Comment> getByBookTitle(String bookTitle);
}
