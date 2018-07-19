package edu.otus.hw05.service.impl;

import edu.otus.hw05.dao.GenreDao;
import edu.otus.hw05.entities.Genre;
import edu.otus.hw05.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public boolean add(Genre genre) {
        return genreDao.insert(genre) > 0;
    }

    @Override
    public boolean remove(Genre genre) {
        return genreDao.remove(genre) > 0;
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getByGenreName(String name) {
        Genre result = null;
        try{
            result = genreDao.getByName(name);
        }catch (EmptyResultDataAccessException e) {
            logger.warn("No genre with name " + name + " found");
        }
        return result;
    }
}
