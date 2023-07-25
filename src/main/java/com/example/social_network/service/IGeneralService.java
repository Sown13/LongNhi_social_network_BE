package com.example.social_network.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t) throws Exception;

    void remove(Long id);
}

