package ru.itis.maletskov.repositories;

import ru.itis.maletskov.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<List<User>> findAllByFirstName(String firstName);
    Optional<User> findOneByEmail(String email);
}
