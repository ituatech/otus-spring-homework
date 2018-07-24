package edu.otus.hw06.repository.impl;


import edu.otus.hw06.entities.Author;
import edu.otus.hw06.repository.AuthorRepository;
import edu.otus.hw06.util.RepositoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(@NotNull Author obj) {
        em.persist(obj);
    }

    @Override
    @Transactional
    public void remove(@NotNull Author obj) {
        obj = getByName(obj.getAuthorName());
        if (obj != null) {
            em.remove(obj);
        }
    }

    @Override
    public Author getByName(String name) {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a WHERE a.authorName = :name", Author.class);
        query.setParameter("name", name);
        return RepositoryUtils.getOneResultFromList(query.getResultList());
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }
}
