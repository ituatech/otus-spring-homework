package edu.otus.hw07.service;

import java.util.List;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface GeneralService<T> {
    void add(T obj);
    void remove(T obj);
    List<T> getAll();
}
