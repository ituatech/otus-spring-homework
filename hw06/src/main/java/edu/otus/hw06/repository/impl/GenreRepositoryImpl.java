package edu.otus.hw06.repository.impl;


import edu.otus.hw06.entities.Author;
import edu.otus.hw06.entities.Genre;
import edu.otus.hw06.repository.GenreRepository;
import edu.otus.hw06.util.RepositoryUtils;
import org.springframework.lang.Nullable;
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
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(@NotNull Genre obj) {
        em.persist(obj);
    }

    @Override
    @Transactional
    public void remove(@NotNull Genre obj) {
        obj = getByName(obj.getGenreName());
        if (obj != null) {
            em.remove(obj);
        }
    }

    @Override
    public Genre getByName(String name) {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.genreName = :name", Genre.class);
        query.setParameter("name", name);
        return RepositoryUtils.getOneResultFromList(query.getResultList());
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }
}
