package edu.otus.hw05.service;

import java.util.List;

/**
 * Created by Nik Bespalov on 17/07/2018.
 *
 * @author Nik Bespalov.
 */
public interface GeneralService<T> {
    boolean add(T obj);
    boolean remove(T obj);
    List<T> getAll();
}
