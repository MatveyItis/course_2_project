package ru.itis.maletskov.repositories;

import ru.itis.maletskov.models.Auth;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth> {
    Optional<Auth> findAuthByCookieValue(String cookieValue);
}
