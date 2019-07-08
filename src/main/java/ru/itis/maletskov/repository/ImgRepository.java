package ru.itis.maletskov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.maletskov.model.Img;

@Repository
public interface ImgRepository extends JpaRepository<Img, Long> {
}
