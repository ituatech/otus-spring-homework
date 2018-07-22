package edu.otus.hw05.dao;

import java.util.List;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface GeneralDao<T> {
    int insert(T obj);
    int remove(T obj);
    List<T> getAll();
}
