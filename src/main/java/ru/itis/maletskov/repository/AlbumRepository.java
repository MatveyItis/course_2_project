package ru.itis.maletskov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.maletskov.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
