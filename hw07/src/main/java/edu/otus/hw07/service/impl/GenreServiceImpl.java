package edu.otus.hw07.service.impl;

import edu.otus.hw07.entities.Genre;
import edu.otus.hw07.repository.GenreRepository;
import edu.otus.hw07.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
@Slf4j
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreDao) {
        this.genreRepository = genreDao;
    }

    @Override
    public void add(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void remove(Genre genre) {
        genreRepository.delete(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getByGenreName(String name) {
        return genreRepository.findByGenreName(name).orElseThrow(() -> {
            log.warn("No genre with name " + name + " found");
            return new NoSuchElementException("No genre with name " + name + " found");
        });
    }
}
