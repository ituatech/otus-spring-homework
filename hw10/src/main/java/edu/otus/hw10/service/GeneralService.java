package edu.otus.hw10.service;

import java.util.List;

public interface GeneralService<T> {
    T add(T obj);
    T update(T book);
    void remove(T obj);
    List<T> getAll();
    T getById(String id);
}
