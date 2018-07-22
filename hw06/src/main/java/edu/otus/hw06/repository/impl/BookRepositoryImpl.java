package edu.otus.hw06.repository.impl;

import edu.otus.hw06.entities.Author;
import edu.otus.hw06.entities.Book;
import edu.otus.hw06.repository.AuthorRepository;
import edu.otus.hw06.repository.BookRepository;
import edu.otus.hw06.repository.GenreRepository;
import edu.otus.hw06.util.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    @Autowired
    public BookRepositoryImpl(AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Book getByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class);
        query.setParameter("title", title);
        return RepositoryUtils.getOneResultFromList(query.getResultList());
    }

    @Override
    @Transactional
    public void insert(@NotNull Book book) {
        book.setAuthors(book.getAuthors()
                .stream()
                .map(author -> {
                    if (authorRepository.getByName(author.getAuthorName()) == null) {
                        em.merge(author);
                    }
                    return authorRepository.getByName(author.getAuthorName());
                })
                .collect(Collectors.toList()));
        book.setGenres(book.getGenres()
                .stream()
                .map(genre -> {
                    if (genreRepository.getByName(genre.getGenreName()) == null) {
                        em.merge(genre);
                    }
                    return genreRepository.getByName(genre.getGenreName());
                })
                .collect(Collectors.toList()));
        em.persist(book);
    }

    @Override
    @Transactional
    public void remove(@NotNull Book obj) {
        obj = getByTitle(obj.getTitle());
        if(obj != null) {
            em.remove(obj);
        }
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

}
