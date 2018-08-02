package edu.otus.hw07.service;

import edu.otus.hw07.entities.Genre;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface GenreService extends GeneralService<Genre> {
    Genre getByGenreName(String name);
}
