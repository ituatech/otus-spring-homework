package edu.otus.hw05.dao;

import edu.otus.hw05.entities.Genre;

import java.util.List;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface GenreDao extends GeneralDao<Genre> {
    List<Genre> getByBookId(int bookId);
    Genre getByName(String name);
}
