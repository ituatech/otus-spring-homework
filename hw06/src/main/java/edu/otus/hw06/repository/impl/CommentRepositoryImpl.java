package edu.otus.hw06.repository.impl;

import edu.otus.hw06.entities.Comment;
import edu.otus.hw06.entities.Genre;
import edu.otus.hw06.repository.CommentRepository;
import edu.otus.hw06.util.RepositoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Nik Bespalov on 22/07/2018.
 *
 * @author Nik Bespalov.
 */
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Comment comment) {
        em.persist(comment);
    }

    @Override
    @Transactional
    public void remove(Comment comment) {
        comment = em.find(Comment.class, comment.getId());
        if (comment != null) {
            em.remove(comment);
        }
    }

    @Override
    public List<Comment> getByBook(String bookTitle) {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c WHERE c.book.title = :book_title", Comment.class);
        query.setParameter("book_title", bookTitle);
        return query.getResultList();
    }
}
