package edu.otus.hw08.service;

import edu.otus.hw08.entities.Genre;

public interface GenreService extends GeneralService<Genre> {
    Genre getByGenreName(String name);
}
