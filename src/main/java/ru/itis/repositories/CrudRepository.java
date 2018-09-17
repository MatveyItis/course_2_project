package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findOne(int id);
    void save(T model);
    void delete(int id);
    Optional<List<T>> findAll();
}
