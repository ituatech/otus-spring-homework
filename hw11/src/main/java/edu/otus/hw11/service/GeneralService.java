package edu.otus.hw11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GeneralService<T> {
    Mono<T> add(T obj);
    Mono<T> update(T book);
    Mono<Void> remove(T obj);
    Flux<T> getAll();
    Mono<T> getById(String id);
}
