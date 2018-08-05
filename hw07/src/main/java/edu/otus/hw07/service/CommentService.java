package edu.otus.hw07.service;

import edu.otus.hw07.entities.Comment;

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
