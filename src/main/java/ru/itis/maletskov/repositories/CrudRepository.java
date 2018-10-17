package ru.itis.maletskov.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findOne(Long id);
    void save(T model);
    void delete(Long id);
    Optional<List<T>> findAll();
}
