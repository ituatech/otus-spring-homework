package edu.otus.hw05.service;

import edu.otus.hw05.entities.Genre;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface GenreService extends GeneralService<Genre> {
    Genre getByGenreName(String name);
}
