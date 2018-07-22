package edu.otus.hw06.service.impl;

import edu.otus.hw06.entities.Genre;
import edu.otus.hw06.repository.GenreRepository;
import edu.otus.hw06.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
@Service
public class GenreServiceImpl implements GenreService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreDao) {
        this.genreRepository = genreDao;
    }

    @Override
    public void add(Genre genre) {
        genreRepository.insert(genre);
    }

    @Override
    public void remove(Genre genre) {
        genreRepository.remove(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.getAll();
    }

    @Override
    public Genre getByGenreName(String name) {
        Genre result = genreRepository.getByName(name);
        if (result == null) {
            logger.warn("No genre with name " + name + " found");
        }
        return result;
    }
}
