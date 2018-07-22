package edu.otus.hw06.repository;

import edu.otus.hw06.entities.Genre;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface GenreRepository extends GeneralRepository<Genre> {
    Genre getByName(String name);
}
