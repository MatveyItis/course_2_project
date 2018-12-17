package ru.itis.maletskov.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findOne(Integer id);

    void save(T model);

    void delete(Integer id);

    Optional<List<T>> findAll();

    void update(T model);
}
