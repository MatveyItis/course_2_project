package ru.itis.maletskov.mappers;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T rowMap(ResultSet resultSet);
}