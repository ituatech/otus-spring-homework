package edu.otus.hw08.service;

import java.util.List;

public interface GeneralService<T> {
    void add(T obj);
    void remove(T obj);
    List<T> getAll();
}
