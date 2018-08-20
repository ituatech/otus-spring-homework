package edu.otus.hw09.service;

import java.util.List;

public interface GeneralService<T> {
    T add(T obj);
    void remove(T obj);
    List<T> getAll();
    T getById(String id);
}
