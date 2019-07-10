package ru.itis.maletskov.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.maletskov.dto.SongDto;
import ru.itis.maletskov.model.Song;
import ru.itis.maletskov.model.User;

@Repository
@Transactional
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("select new ru.itis.maletskov.dto.SongDto(" +
            "   s, " +
            "   count(sl), " +
            "   (sum(case when sl = :user then 1 else 0 end) > 0) " +
            ") " +
            "from Song s left join s.likes sl " +
            "left join s.songImg img " +
            "group by s")
    Page<SongDto> findAll(Pageable pageable, @Param("user") User user);

    @Query("select new ru.itis.maletskov.dto.SongDto(" +
            "   s, " +
            "   count(sl), " +
            "   (sum(case when sl = :user then 1 else 0 end) > 0)" +
            ") " +
            "from Song s left join s.likes sl " +
            "left join s.songImg img " +
            "where s.tag = :tag " +
            "group by s")
    Page<SongDto> findByTag(@Param("tag") String tag, Pageable pageable, @Param("user") User user);

    @Query("select new ru.itis.maletskov.dto.SongDto(" +
            "   s, " +
            "   count(sl), " +
            "   (sum(case when sl = :user then 1 else 0 end) > 0) " +
            ") " +
            "from Song s left join s.likes sl " +
            "left join s.songImg img " +
            "where s.author = :author " +
            "group by s")
    Page<SongDto> findByUser(Pageable pageable, @Param("author") User author, @Param("user") User user);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from songs where id = :id")
    void deleteSongById(Long id);
}
