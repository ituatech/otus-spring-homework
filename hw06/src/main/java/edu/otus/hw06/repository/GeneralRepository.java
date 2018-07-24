package edu.otus.hw06.repository;

import java.util.List;

/**
 * Created by Nik Bespalov on 15/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface GeneralRepository<T> {
    void insert(T obj);
    void remove(T obj);
    List<T> getAll();
}
